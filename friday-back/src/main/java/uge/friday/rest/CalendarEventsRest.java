package uge.friday.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import uge.friday.data.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/calendar-events")
public class CalendarEventsRest {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws JsonProcessingException {
        CalendarEvent event = new CalendarEvent(
                new CalendarDate(10,2,2021, new CalendarTime(7,30)),
                new CalendarDate(12,2,2021, new CalendarTime(10,30)),
                EventRecurrenceEnum.NONE,
                CalendarTypeEnum.FRIDAY,
                "Paris", "Vacances");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(event);

        return json;
    }

//    public Event getEvents(month){
//        //R1
//        //R2
//        //R3
//    }
}