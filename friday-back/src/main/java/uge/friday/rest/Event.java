package uge.friday.rest;

import javax.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * Database data persistence class.
 * extends PanacheEntity
 */
@Entity
public class Event extends PanacheEntity {
    public String title;
    //public String description;

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                '}';
    }

}
