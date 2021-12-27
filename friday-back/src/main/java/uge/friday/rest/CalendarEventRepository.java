package uge.friday.rest;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import uge.friday.data.CalendarEvent;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CalendarEventRepository implements PanacheRepository<CalendarEvent> {

    public List<CalendarEvent> getEvents(){
        return list("");
    }

}
