package uge.friday.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {

    public static void main(String[] args) throws JsonProcessingException {
        CalendarEvent event = new CalendarEvent(
                1,
                new CalendarDate(10,2,2021, new CalendarTime(7,30)),
                new CalendarDate(12,2,2021, new CalendarTime(10,30)),
                EventRecurrenceEnum.NONE,
                CalendarTypeEnum.FRIDAY,
                "Vacances","Paris", "Vacances a la mer", false);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(event);
        System.out.println(json);
    }
}
