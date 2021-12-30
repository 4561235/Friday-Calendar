package uge.friday.test;

import org.junit.jupiter.api.Test;
import uge.friday.data.CalendarTime;

import static org.junit.jupiter.api.Assertions.*;

class CalendarTimeTest {

    @Test
    void calendarTimeDataHourInferiorIAE() {
        assertThrows(IllegalArgumentException.class, () -> new CalendarTime(-1,59));
    }

    @Test
    void calendarTimeDataHourSuperiorIAE() {
        assertThrows(IllegalArgumentException.class, () -> new CalendarTime(24,59));
    }

    @Test
    void calendarTimeDataMinuteInferiorIAE() {
        assertThrows(IllegalArgumentException.class, () -> new CalendarTime(0,-1));
    }

    @Test
    void calendarTimeDataMinuteSuperiorIAE() {
        assertThrows(IllegalArgumentException.class, () -> new CalendarTime(0,60));
    }

    @Test
    void getHour() {
        var calendarTime = new CalendarTime(23,0);
        assertEquals(23, calendarTime.getHour());
    }

    @Test
    void getMinute() {
        var calendarTime = new CalendarTime(0,59);
        assertEquals(59, calendarTime.getMinute());
    }

    @Test
    void testToString() {
        var calendarTime = new CalendarTime(23,59);
        assertEquals("CalendarTime{hour=23, minute=59}", calendarTime.toString());
    }

}