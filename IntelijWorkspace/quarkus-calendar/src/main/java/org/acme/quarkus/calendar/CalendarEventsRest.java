package org.acme.quarkus.calendar;

import fr.uge.calendar.CalendarEvent;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/calendar-events")
public class CalendarEventsRest {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        CalendarEvent event = new CalendarEvent();
        return event.toString();
        //return "Hello RESTEasy";
    }
}