package uge.friday.rest;

import uge.friday.data.*;

import java.util.ArrayList;
import java.util.List;

public class PseudoTestDatabase {
    private final ArrayList<CalendarEvent> calendarEvents = new ArrayList<>();

    PseudoTestDatabase(){
        CalendarEvent event = new CalendarEvent(
                1,
                new CalendarDate(10,12,2021, new CalendarTime(7,30)),
                new CalendarDate(12,12,2021, new CalendarTime(10,30)),
                EventRecurrenceEnum.NONE,
                CalendarTypeEnum.FRIDAY,
                "Repos a Hawai","Paris", "");

        CalendarEvent event2 = new CalendarEvent(
                2,
                new CalendarDate(17,12,2021, new CalendarTime(7,30)),
                new CalendarDate(19,12,2021, new CalendarTime(10,30)),
                EventRecurrenceEnum.NONE,
                CalendarTypeEnum.FRIDAY,
                "Projet L3","Paris", "Faire projet L3");

        CalendarEvent event3 = new CalendarEvent(
                3,
                new CalendarDate(21,12,2021, new CalendarTime(7,30)),
                new CalendarDate(22,12,2021, new CalendarTime(10,30)),
                EventRecurrenceEnum.NONE,
                CalendarTypeEnum.FRIDAY,
                "Truc","Paris", "Faire truc");
        CalendarEvent event4 = new CalendarEvent(
                4,
                new CalendarDate(30,12,2021, new CalendarTime(7,30)),
                new CalendarDate(31,12,2021, new CalendarTime(10,30)),
                EventRecurrenceEnum.NONE,
                CalendarTypeEnum.FRIDAY,
                "Rendre projet","Paris", "Rendre projet Java");
        CalendarEvent event5 = new CalendarEvent(
                5,
                new CalendarDate(20,1,2022, new CalendarTime(7,30)),
                new CalendarDate(25,1,2022, new CalendarTime(10,30)),
                EventRecurrenceEnum.NONE,
                CalendarTypeEnum.FRIDAY,
                "Acheter telephone","Paris", "Acheter tel desc");
        CalendarEvent event6 = new CalendarEvent(
                6,
                new CalendarDate(30,1,2022, new CalendarTime(7,30)),
                new CalendarDate(31,1,2022, new CalendarTime(10,30)),
                EventRecurrenceEnum.NONE,
                CalendarTypeEnum.FRIDAY,
                "Me reposer","Paris", "Me reposer desc");
        calendarEvents.add(event);
        calendarEvents.add(event2);
        calendarEvents.add(event3);
        calendarEvents.add(event4);
        calendarEvents.add(event5);
        calendarEvents.add(event6);
    }

    public List<CalendarEvent> getEvents(){
        return List.copyOf(this.calendarEvents);
    }

    public void removeEvent(int id){
        for(int i = 0; i < this.calendarEvents.size(); i++){
            CalendarEvent event = calendarEvents.get(i);
            if(event.getId() == id){
                this.calendarEvents.remove(i);
            }
        }
    }

}
