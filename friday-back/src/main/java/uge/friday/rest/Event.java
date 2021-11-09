package uge.friday.rest;

import javax.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * Database data persistence class.
 */
@Entity
public class Event extends PanacheEntity {
    public String title;
    public String description;
}
