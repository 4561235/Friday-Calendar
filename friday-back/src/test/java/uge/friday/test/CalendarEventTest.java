package uge.friday.test;

import org.junit.jupiter.api.Test;
import uge.friday.data.*;

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

    @Test
    public void calendarEventToString() {
        var calendarEvent = new CalendarEvent(new CalendarDate(31, 12, 2021, new CalendarTime(23, 59)), new CalendarDate(31, 12, 2021, new CalendarTime(23, 59)), EventRecurrenceEnum.NONE, CalendarTypeEnum.FRIDAY, "valid", "valid", "valid", true);
        assertEquals("CalendarEvent{" +
                "from=CalendarDate{day=31, month=12, year=2021, time=CalendarTime{hour=23, minute=59}}, " +
                "to=CalendarDate{day=31, month=12, year=2021, time=CalendarTime{hour=23, minute=59}}, " +
                "title=valid, location='valid', description='valid', recurrence=NONE, calendarType=FRIDAY}", calendarEvent.toString());
    }

}