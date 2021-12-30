package uge.friday.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventRecurrenceGenerator {

    private final List<CalendarEvent> eventsList;

    public  EventRecurrenceGenerator(List<CalendarEvent> eventsList){
        Objects.requireNonNull(eventsList);
        this.eventsList = List.copyOf(eventsList);
    }

    public String generateRecurrentEvents(int year, int month){
        if(year < 0) throw new IllegalArgumentException("Year can't be < 0");
        if(month < 1 || month > 12) throw new IllegalArgumentException("Month must be between 1 and 12");


        final ObjectMapper mapper = new ObjectMapper();

        // Find all events for this combination year month without recurrence.
        var eventsWithoutRecurrence = getEventsWithoutReccurence(year, month);

        // Calculates new events by recurrence.
        var newEvents = calculateRecurrentEvents(year, month);

        // Convert all events to json array for display.
        String events = Stream.concat(eventsWithoutRecurrence.stream(), newEvents.stream())
                .map(e -> {
                    try {
                        return mapper.writeValueAsString(e);
                    } catch (JsonProcessingException ex) { /* Do nothing. */ }
                    return null;
                })
                .collect(Collectors.joining(",", "[", "]"));

        return events;
    }

    private List<CalendarEvent> getEventsWithoutReccurence(int year, int month){
        return eventsList.stream().filter(e -> {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("y-M-d");
                    try {
                        Date from = dateFormat.parse(e.getFrom().getYear() + "-" +e.getFrom().getMonth() + "-1");
                        Date to = dateFormat.parse(e.getTo().getYear() +"-" +e.getTo().getMonth() + "-1");
                        Date current = dateFormat.parse(year +"-" + month + "-1");
                        return from.compareTo(current) <= 0 && to.compareTo(current) >= 0 && e.getRecurrence() == EventRecurrenceEnum.NONE;
                    } catch (ParseException ex) {
                        return false;
                    }
                }).collect(Collectors.toList());
    }

    private List<CalendarEvent> calculateRecurrentEvents(int year, int month){
        var eventsRecurrent = eventsList.stream()
                // Events start before/during the combination year month.
                .filter(e -> (e.getFrom().getYear() <= year && e.getFrom().getMonth() <= month) || e.getFrom().getYear() < year)
                .filter(e -> e.getRecurrence() != EventRecurrenceEnum.NONE)
                .collect(Collectors.toList());

        // Calculates new events by recurrence.
        return eventsRecurrent.stream()
                .map(e -> this.recurrenceToEvents(e, year, month))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
	
	/**
     * @param event with recurrence
     * @param yearToDisplay years of events to display
     * @param monthToDisplay month of events to display
     * @return Generate events in relation to an event that has a recurrence
     */
    private List<CalendarEvent> recurrenceToEvents(CalendarEvent event, int yearToDisplay, int monthToDisplay) {
        Objects.requireNonNull(event);
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
