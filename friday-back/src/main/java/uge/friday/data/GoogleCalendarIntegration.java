package uge.friday.data;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GoogleCalendarIntegration {

    private final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
    private final String TOKENS_DIRECTORY_PATH = "tokens";



    public List<CalendarEvent> getCalendarEvents() throws GeneralSecurityException, IOException {
        ArrayList<CalendarEvent> calendarEventsList = new ArrayList<>();
        List<Event> googleEvents = this.getGoogleCalendarEvents();

        for (Event googleEvent : googleEvents) {
            String summary = googleEvent.getSummary();
            if(summary == null) summary = "";

            String location = googleEvent.getLocation();
            if(location == null) location = "";

            String description = googleEvent.getDescription();
            if(description == null || description.length() >= 255) description = "";

            java.util.Calendar calFrom = java.util.Calendar.getInstance();
            java.util.Calendar calTo = java.util.Calendar.getInstance();
            DateTime from = googleEvent.getStart().getDateTime();
            DateTime to = googleEvent.getEnd().getDateTime();


            if(from != null || to != null) {
                calFrom.setTime(new Date(from.getValue()));
                calTo.setTime(new Date(to.getValue()));
                CalendarEventBuilder eventBuilder = new CalendarEventBuilder(summary,
                        new CalendarDate(calFrom.get(java.util.Calendar.DAY_OF_MONTH), calFrom.get(java.util.Calendar.MONTH) + 1, calFrom.get(java.util.Calendar.YEAR), new CalendarTime(calFrom.get(java.util.Calendar.HOUR), calFrom.get(java.util.Calendar.MINUTE))),
                        new CalendarDate(calTo.get(java.util.Calendar.DAY_OF_MONTH), calTo.get(java.util.Calendar.MONTH) + 1, calTo.get(java.util.Calendar.YEAR), new CalendarTime(calTo.get(java.util.Calendar.HOUR), calTo.get(java.util.Calendar.MINUTE))));

                eventBuilder.setRecurrence(EventRecurrenceEnum.NONE)
                            .setCalendarType(CalendarTypeEnum.GOOGLECAL)
                            .setLocation(location)
                            .setDescription(description)
                            .setAllDay(false);

                calendarEventsList.add(eventBuilder.build());
            }
        }
        return calendarEventsList;
    }

    private List<Event> getGoogleCalendarEvents() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName("Friday").build();

        Events events = service.events().list("primary").setPageToken(null).execute();
        return events.getItems();
    }

    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        InputStream in = GoogleCalendarIntegration.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
}
