package uge.friday.rest;

import javax.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import uge.friday.data.CalendarDate;

/**
 * Database data persistence class.
 * extends PanacheEntity
 */
@Entity
public class Event extends PanacheEntity {
    private enum CalendarType {FRIDAY, GOOGLE}

    public String title;
    public String location;
    public String description;
    public boolean recurrence;
    public CalendarType calendarType;
    public CalendarDate start;
    public CalendarDate end;


    public static void update(Event event) {/* The update method must define the fields to modify and set it. */};

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                '}';
    }

}
