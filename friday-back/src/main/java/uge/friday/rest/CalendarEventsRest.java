package uge.friday.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.config.SysPropConfigSource;
import uge.friday.data.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jboss.resteasy.annotations.jaxrs.PathParam;
import javax.ws.rs.core.Response;

@Path("/calendar-events")
public class CalendarEventsRest {

    private PseudoTestDatabase testDatabase = new PseudoTestDatabase();
    private CalendarEventRepository repository = new CalendarEventRepository();
    private ObjectMapper mapper = new ObjectMapper();

    //Imaginons que l'utilisateur veut voir les events de de decembre 2021 alors react va appeler cette
    //methode en donnant l'annee et le mois en parametre. Cette methode va devoir interoger la base de donnees
    //et renvoyer tout les events en forme de JSON en utilisant Jackson
    @Path("getEvents/{year}/{month}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String getEvents(@PathParam int year, @PathParam int month) throws JsonProcessingException {
        List<CalendarEvent> eventsList = repository.getEvents();

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
                .map(e -> recurrentToEvents(e, year, month))
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


    public static List<CalendarEvent> recurrentToEvents(CalendarEvent event, int yearToDisplay, int monthToDisplay) {
        var events = new ArrayList<CalendarEvent>();
        var daysInDisplayMonth = YearMonth.of(yearToDisplay, monthToDisplay).lengthOfMonth();
        var dayRecurrenceStart = 1;
        if (event.getFrom().getYear() == yearToDisplay && event.getFrom().getMonth() == monthToDisplay) dayRecurrenceStart = event.getFrom().getDay();
        switch (event.getRecurrence()) {
            case NONE -> { /* Nothing. */ }
            case DAILY -> {
                for (var day = dayRecurrenceStart; day <= daysInDisplayMonth; day++) {
                    var newCalendarDateFrom = new CalendarDate(day, monthToDisplay, yearToDisplay, event.getFrom().getTime());
                    events.add(new CalendarEvent(newCalendarDateFrom, newCalendarDateFrom, event.getRecurrence(), event.getCalendarType(), event.getTitle(), event.getLocation(), event.getDescription(), event.isAllDay()));
                }
            }
            case WEEKLY -> {
                for (var day = dayRecurrenceStart; day <= daysInDisplayMonth; day+=7) {
                    var newCalendarDateFrom = new CalendarDate(day, monthToDisplay, yearToDisplay, event.getFrom().getTime());
                    events.add(new CalendarEvent(newCalendarDateFrom, newCalendarDateFrom, event.getRecurrence(), event.getCalendarType(), event.getTitle(), event.getLocation(), event.getDescription(), event.isAllDay()));
                }
            }
            case MONTHLY -> {
                var day = Math.min(event.getFrom().getDay(), daysInDisplayMonth);
                var newCalendarDateFrom = new CalendarDate(day, monthToDisplay, yearToDisplay, event.getFrom().getTime());
                events.add(new CalendarEvent(newCalendarDateFrom, newCalendarDateFrom, event.getRecurrence(), event.getCalendarType(), event.getTitle(), event.getLocation(), event.getDescription(), event.isAllDay()));
            }
            case YEARLY -> {
                var day = Math.min(event.getFrom().getDay(), daysInDisplayMonth);
                var newCalendarDateFrom = new CalendarDate(day, event.getFrom().getMonth(), yearToDisplay, event.getFrom().getTime());
                events.add(new CalendarEvent(newCalendarDateFrom, newCalendarDateFrom, event.getRecurrence(), event.getCalendarType(), event.getTitle(), event.getLocation(), event.getDescription(), event.isAllDay()));
            }
        }
        return events;
    }


    
    //Supprime l'event de la BDD avec un id specifique
    
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
    
    //Lis l'eventJson et le rajoute dans la BDD
    
    @Path("addEvent/{eventJson}")
    @GET
    @Transactional
    public void addEvent(@PathParam String eventJson) throws JsonProcessingException {
        CalendarEvent event = mapper.readValue(eventJson, CalendarEvent.class);
        repository.persist(event);
    }

    //Lis le string en format ical et rajoute les events dans la BDD
    
    @Path("sendIcal/{icalString}")
    @GET
    @Transactional
    public void addIcalEvents(@PathParam String icalString){

        System.out.println(icalString);
        IcalReader reader = new IcalReader();
        List<CalendarEvent> list = reader.readIcal(icalString);
        for(CalendarEvent event : list){
            repository.persist(event);
        }
    }

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
