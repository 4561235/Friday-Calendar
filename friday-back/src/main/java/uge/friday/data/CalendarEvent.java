package uge.friday.data;

import java.util.Objects;

public class CalendarEvent {

    private final CalendarDate from;
    private final CalendarDate to;
    private final String location;
    private final String description;
    private final EventRecurrenceEnum recurrence;
    private final CalendarTypeEnum calendarType;

    public CalendarEvent(CalendarDate from, CalendarDate to, EventRecurrenceEnum recurrence, CalendarTypeEnum calendarType, String location, String description){
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        Objects.requireNonNull(description);
        Objects.requireNonNull(location);

        this.from = from;
        this.to = to;
        this.recurrence = recurrence;
        this.calendarType = calendarType;
        this.location = location;
        this.description = description;
    }

    public CalendarDate getFrom() {
        return from;
    }

    public CalendarDate getTo() {
        return to;
    }

    public EventRecurrenceEnum getRecurrence() {
        return recurrence;
    }

    public CalendarTypeEnum getCalendarType() {
        return calendarType;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return "CalendarEvent{" +
                "from=" + from +
                ", to=" + to +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", recurrence=" + recurrence +
                ", calendarType=" + calendarType +
                '}';
    }
}