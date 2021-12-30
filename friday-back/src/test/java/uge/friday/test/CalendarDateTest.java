package uge.friday.test;

import org.junit.jupiter.api.Test;
import uge.friday.data.CalendarDate;
import uge.friday.data.CalendarTime;

import static org.junit.jupiter.api.Assertions.*;

class CalendarDateTest {

    @Test
    public void calendarDateDataTimeNullNPE() {
        assertThrows(NullPointerException.class, () -> new CalendarDate(31, 12, 2021, null));
    }

    @Test
    public void calendarDateDataMonthInferiorIAE() {
        assertThrows(IllegalArgumentException.class, () -> new CalendarDate(31, 0, 2021, new CalendarTime()));
    }

    @Test
    public void calendarDateDataMonthSuperiorIAE() {
        assertThrows(IllegalArgumentException.class, () -> new CalendarDate(31, 13, 2021, new CalendarTime()));
    }

    @Test
    public void calendarDateDataYearInferiorIAE() {
        assertThrows(IllegalArgumentException.class, () -> new CalendarDate(31, 12, -1, new CalendarTime()));
    }

    @Test
    public void calendarDateDataYearSuperiorIAE() {
        assertThrows(IllegalArgumentException.class, () -> new CalendarDate(31, 12, 4001, new CalendarTime()));
    }

    @Test
    void getDay() {
        var calendarDate = new CalendarDate(31, 12, 2021, new CalendarTime());
        assertEquals(31, calendarDate.getDay());
    }

    @Test
    void getMonth() {
        var calendarDate = new CalendarDate(31, 12, 2021, new CalendarTime());
        assertEquals(12, calendarDate.getMonth());
    }

    @Test
    void getYear() {
        var calendarDate = new CalendarDate(31, 12, 2021, new CalendarTime());
        assertEquals(2021, calendarDate.getYear());
    }

    @Test
    void getTime() {
        var calendarTime = new CalendarTime(23,59);
        var calendarDate = new CalendarDate(31, 12, 2021, calendarTime);
        assertEquals(calendarTime, calendarDate.getTime());
    }

    @Test
    void testToString() {
        var calendarDate = new CalendarDate(31, 12, 2021, new CalendarTime(23,59));
        assertEquals("CalendarDate{day=31, month=12, year=2021, time=CalendarTime{hour=23, minute=59}}", calendarDate.toString());
    }
}