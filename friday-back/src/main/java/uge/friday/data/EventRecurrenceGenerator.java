package uge.friday.data;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class EventRecurrenceGenerator {

    /**
     * @param event with recurrence
     * @param yearToDisplay years of events to display
     * @param monthToDisplay month of events to display
     * @return Generate events in relation to an event that has a recurrence
     */
    public List<CalendarEvent> recurrenceToEvents(CalendarEvent event, int yearToDisplay, int monthToDisplay) {
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
}
