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



    public void deleteEvent(long id){
        delete("id", id);
    }


    public void updateEvent(long id, CalendarEvent event){
        CalendarEvent ev = this.findById(id);
        ev.setTitle(event.getTitle());
        ev.setDescription(event.getDescription());
        ev.setLocation(event.getLocation());
        ev.setCalendarType(event.getCalendarType());
        ev.setRecurrence(event.getRecurrence());
        ev.setFrom(event.getFrom());
        ev.setTo(event.getTo());
        ev.setAllDay(event.isAllDay());

        this.persist(ev);
    }

}
