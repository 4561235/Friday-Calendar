package uge.friday.data;

import java.util.Objects;

public class CalendarEventBuilder {

    private CalendarDate from;
    private CalendarDate to;
    private String title;

    private String location = "";
    private String description = "";
    private EventRecurrenceEnum recurrence = EventRecurrenceEnum.NONE;
    private CalendarTypeEnum calendarType = CalendarTypeEnum.FRIDAY;
    private boolean allDay = false;

    public CalendarEventBuilder(String title, CalendarDate from, CalendarDate to){
        Objects.requireNonNull(title);
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        this.title = title;
        this.from = from;
        this.to = to;
    }

    public CalendarEventBuilder setLocation(String location) {
        Objects.requireNonNull(location);
        this.location = location;
        return this;
    }

    public CalendarEventBuilder setDescription(String description) {
        Objects.requireNonNull(description);
        this.description = description;
        return this;
    }

    public CalendarEventBuilder setRecurrence(EventRecurrenceEnum recurrence) {
        Objects.requireNonNull(recurrence);
        this.recurrence = recurrence;
        return this;
    }

    public CalendarEventBuilder setCalendarType(CalendarTypeEnum calendarType) {
        Objects.requireNonNull(calendarType);
        this.calendarType = calendarType;
        return this;
    }

    public CalendarEventBuilder setAllDay(boolean allDay) {
        this.allDay = allDay;
        return this;
    }

    public CalendarEvent build(){
        CalendarEvent event = new CalendarEvent(
                this.from, this.to, this.recurrence, this.calendarType,
                this.title, this.location, this.description, this.allDay);
        return event;
    }
}
