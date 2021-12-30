package uge.friday.test;

import org.junit.jupiter.api.Test;
import uge.friday.data.CalendarDate;
import uge.friday.data.CalendarEvent;
import uge.friday.data.CalendarTypeEnum;
import uge.friday.data.EventRecurrenceEnum;

import static org.junit.jupiter.api.Assertions.*;

class CalendarEventTest {

    @Test
    public void calendarEventDataFromNullNPE() {
        assertThrows(NullPointerException.class, () -> new CalendarEvent(null, new CalendarDate(), EventRecurrenceEnum.NONE, CalendarTypeEnum.FRIDAY, "valid", "valid", "valid", true));
    }

    @Test
    public void calendarEventDataToNullNPE() {
        assertThrows(NullPointerException.class, () -> new CalendarEvent(new CalendarDate(), null, EventRecurrenceEnum.NONE, CalendarTypeEnum.FRIDAY, "valid", "valid", "valid", true));
    }

    @Test
    public void calendarEventDataRecurrenceNullNPE() {
        assertThrows(NullPointerException.class, () -> new CalendarEvent(new CalendarDate(), new CalendarDate(), null, CalendarTypeEnum.FRIDAY, "valid", "valid", "valid", true));
    }

    @Test
    public void calendarEventDataTitleNullNPE() {
        assertThrows(NullPointerException.class, () -> new CalendarEvent(new CalendarDate(), new CalendarDate(), EventRecurrenceEnum.NONE, CalendarTypeEnum.FRIDAY, null, "valid", "valid", true));
    }

    @Test
    public void calendarEventDataLocationNullNPE() {
        assertThrows(NullPointerException.class, () -> new CalendarEvent(new CalendarDate(), new CalendarDate(), EventRecurrenceEnum.NONE, CalendarTypeEnum.FRIDAY, "valid", null, "valid", true));
    }

    @Test
    public void calendarEventDataDescriptionNullNPE() {
        assertThrows(NullPointerException.class, () -> new CalendarEvent(new CalendarDate(), new CalendarDate(), EventRecurrenceEnum.NONE, CalendarTypeEnum.FRIDAY, "valid", "valid", null, true));
    }
}