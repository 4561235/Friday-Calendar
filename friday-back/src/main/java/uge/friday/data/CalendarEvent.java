package uge.friday.data;

import java.util.Objects;

public class CalendarEvent {

    private final int id;
    private final CalendarDate from;
    private final CalendarDate to;
    private final String title;
    private final String location;
    private final String description;
    private final EventRecurrenceEnum recurrence;
    private final CalendarTypeEnum calendarType;
    private final boolean allDay;

    public CalendarEvent(int id, CalendarDate from, CalendarDate to, EventRecurrenceEnum recurrence, CalendarTypeEnum calendarType, String title, String location, String description, boolean allDay){
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        Objects.requireNonNull(title);
        Objects.requireNonNull(description);
        Objects.requireNonNull(location);

        this.id = id;
        this.title = title;
        this.from = from;
        this.to = to;
        this.recurrence = recurrence;
        this.calendarType = calendarType;
        this.location = location;
        this.description = description;
        this.allDay = allDay;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
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

    public boolean isAllDay(){return allDay;}


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