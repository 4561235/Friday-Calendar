package uge.friday.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {

    public static void main(String[] args) throws JsonProcessingException {
        CalendarEvent event = new CalendarEvent(new CalendarDate(10,2,2021), new CalendarDate(12,2,2021), "Vacances");
        //CalendarDate date = new CalendarDate(10,2,2021);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(event);
        System.out.println(json);
    }
}
