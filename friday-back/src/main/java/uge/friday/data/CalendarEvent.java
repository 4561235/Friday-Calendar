package uge.friday.data;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CalendarEvent extends PanacheEntity {

//    private int id;
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

    public CalendarEvent(){
//        this.id = 0;
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

//        this.id = id;
        this.title = title;
        this.from = from;
        this.to = to;
        this.recurrence = recurrence;
        this.calendarType = calendarType;
        this.location = location;
        this.description = description;
        this.allDay = allDay;
    }

//    public int getId() {
//        return id;
//    }

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