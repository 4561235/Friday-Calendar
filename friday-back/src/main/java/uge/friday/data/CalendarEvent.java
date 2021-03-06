package uge.friday.data;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class CalendarEvent extends PanacheEntity {

    @ManyToOne(cascade = {CascadeType.ALL})
    private CalendarDate from;
    @ManyToOne(cascade = {CascadeType.ALL})
    private CalendarDate to;
    private String title;
    private String location;
    private String description;
    private EventRecurrenceEnum recurrence;
    private CalendarTypeEnum calendarType;
    private boolean allDay;

    //Default constructor is needed for panache
    public CalendarEvent(){
        this.title = "";
        this.from = null;
        this.to = null;
        this.recurrence = EventRecurrenceEnum.NONE;
        this.calendarType = CalendarTypeEnum.FRIDAY;
        this.location = "";
        this.description = "";
        this.allDay = false;
    }

    public CalendarEvent(CalendarDate from, CalendarDate to, EventRecurrenceEnum recurrence, CalendarTypeEnum calendarType, String title, String location, String description, boolean allDay){
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        Objects.requireNonNull(title);
        Objects.requireNonNull(description);
        Objects.requireNonNull(location);
        Objects.requireNonNull(recurrence);

        this.title = title;
        this.from = from;
        this.to = to;
        this.recurrence = recurrence;
        this.calendarType = calendarType;
        this.location = location;
        this.description = description;
        this.allDay = allDay;
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

    public void setFrom(CalendarDate from) {
        this.from = from;
    }

    public void setTo(CalendarDate to) {
        this.to = to;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRecurrence(EventRecurrenceEnum recurrence) {
        this.recurrence = recurrence;
    }

    public void setCalendarType(CalendarTypeEnum calendarType) {
        this.calendarType = calendarType;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    @Override
    public String toString() {
        return "CalendarEvent{" +
                "from=" + from +
                ", to=" + to +
                ", title=" +title +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", recurrence=" + recurrence +
                ", calendarType=" + calendarType +
                '}';
    }
}