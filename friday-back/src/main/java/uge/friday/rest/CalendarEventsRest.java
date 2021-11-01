package uge.friday.rest;

import uge.friday.data.CalendarDate;
import uge.friday.data.CalendarEvent;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/calendar-events")
public class CalendarEventsRest {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        CalendarEvent event = new CalendarEvent(new CalendarDate(10,2,2021), new CalendarDate(12,2,2021), "Vacances");
        return event.toString();
        //return "Hello RESTEasy";
    }
}