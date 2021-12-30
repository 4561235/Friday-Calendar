package uge.friday.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import uge.friday.data.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/calendar-events")
public class CalendarEventsRest {

    private final CalendarEventRepository repository = new CalendarEventRepository();
    private final ObjectMapper mapper = new ObjectMapper();

    //React will call this method to get events from a month in a year this method need to
    //return a json array of events

    @Path("getEvents/{year}/{month}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String getEvents(@PathParam int year, @PathParam int month) throws JsonProcessingException {
        List<CalendarEvent> eventsList = repository.getEvents();
        var eventRecGen = new EventRecurrenceGenerator();

        // Find all events for this combination year month without recurrence.
        var eventsWithoutRecurrence = eventsList.stream()
                .filter(e -> e.getFrom().getMonth() == month && e.getFrom().getYear() == year && e.getRecurrence() == EventRecurrenceEnum.NONE)
                .collect(Collectors.toList());

        // Find recurrent events.
        var eventsRecurrent = eventsList.stream()
                // Events start before/during the combination year month.
                .filter(e -> (e.getFrom().getYear() <= year && e.getFrom().getMonth() <= month) || e.getFrom().getYear() < year)
                .filter(e -> e.getRecurrence() != EventRecurrenceEnum.NONE)
                .collect(Collectors.toList());

        // Calculates new events by recurrence.
        var newEvents = eventsRecurrent.stream()
                .map(e -> eventRecGen.recurrenceToEvents(e, year, month))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        // Convert all events for display.
        var events = Stream.concat(eventsWithoutRecurrence.stream(), newEvents.stream())
                .map(e -> {
                    try {
                        return mapper.writeValueAsString(e);
                    } catch (JsonProcessingException ex) { /* Do nothing. */ }
                    return null;
                })
                .collect(Collectors.joining(",", "[", "]"));
        return events;
    }

    //Delete a calendar event for database using it's id
    
    @Path("deleteEvent/{id}")
    @GET
    @Transactional
    public void deleteEvent(@PathParam long id){
        repository.deleteEvent(id);
    }
    
    //Met a jour l'event dans la BDD. Il faut lire le Json envoye par react
    //Todo methode update in Event, the update method must define the fields to modify and set it
    
    @Path("updateEvent/{id}/{eventJson}")
    @GET
    @Transactional
    public void updateEvent(@PathParam long id, @PathParam String eventJson) throws JsonProcessingException {
        CalendarEvent event = mapper.readValue(eventJson, CalendarEvent.class);
        repository.updateEvent(id, event);
    }
    
    //Convert the event in json format to CalendarEvent class and add it to database
    
    @Path("addEvent/{eventJson}")
    @GET
    @Transactional
    public void addEvent(@PathParam String eventJson) throws JsonProcessingException {
        CalendarEvent event = mapper.readValue(eventJson, CalendarEvent.class);
        repository.persist(event);
    }

    //Read the ical format string, converts IcalEvents to CalendarEvents and add it to database
    
    @Path("sendIcal/{icalString}")
    @GET
    @Transactional
    public void addIcalEvents(@PathParam String icalString){
        IcalReader reader = new IcalReader();
        List<CalendarEvent> list = reader.readIcal(icalString);
        for(CalendarEvent event : list){
            repository.persist(event);
        }
    }

    //Get events from google calendar, converts GoogleCalendarEvents to CalendarEvents and add it to database

    @Path("connectToGoogleCalendar/")
    @GET
    @Transactional
    public void connectToGoogleCalendar() throws GeneralSecurityException, IOException {
        GoogleCalendarIntegration googleCal = new GoogleCalendarIntegration();
        for(CalendarEvent calEvent : googleCal.getCalendarEvents()){
            this.repository.persist(calEvent);
        }
    }


}
