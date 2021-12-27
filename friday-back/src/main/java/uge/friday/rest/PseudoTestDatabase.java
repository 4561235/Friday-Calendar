package uge.friday.rest;

import uge.friday.data.*;

import java.util.ArrayList;
import java.util.List;

public class PseudoTestDatabase {
    private final ArrayList<CalendarEvent> calendarEvents = new ArrayList<>();

    PseudoTestDatabase(){
//        CalendarEvent event = new CalendarEvent(
//                1,
//                new CalendarDate(10,12,2021, new CalendarTime(7,30)),
//                new CalendarDate(12,12,2021, new CalendarTime(10,30)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Repos a Hawai","Paris", "",false);
//
//        CalendarEvent event2 = new CalendarEvent(
//                2,
//                new CalendarDate(17,12,2021, new CalendarTime(7,30)),
//                new CalendarDate(19,12,2021, new CalendarTime(10,30)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Projet L3","Paris", "Faire projet L3",false);
//
//        CalendarEvent event3 = new CalendarEvent(
//                3,
//                new CalendarDate(21,12,2021, new CalendarTime(7,30)),
//                new CalendarDate(22,12,2021, new CalendarTime(10,30)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Truc","Paris", "Faire truc",false);
//        CalendarEvent event4 = new CalendarEvent(
//                4,
//                new CalendarDate(30,12,2021, new CalendarTime(7,30)),
//                new CalendarDate(31,12,2021, new CalendarTime(10,30)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Rendre projet","Paris", "Rendre projet Java",false);
//        CalendarEvent event5 = new CalendarEvent(
//                5,
//                new CalendarDate(20,1,2022, new CalendarTime(7,30)),
//                new CalendarDate(25,1,2022, new CalendarTime(10,30)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Acheter telephone","Paris", "Acheter tel desc",true);
//        CalendarEvent event6 = new CalendarEvent(
//                6,
//                new CalendarDate(30,1,2022, new CalendarTime(7,30)),
//                new CalendarDate(31,1,2022, new CalendarTime(10,30)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Me reposer","Paris", "Me reposer desc", false);
//
//
//
//        CalendarEvent event7 = new CalendarEvent(
//                7,
//                new CalendarDate(22,12,2021, new CalendarTime(7,30)),
//                new CalendarDate(23,12,2021, new CalendarTime(10,30)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Cerises","Paris", "Je test desc", false);
//        CalendarEvent event8 = new CalendarEvent(
//                8,
//                new CalendarDate(22,12,2021, new CalendarTime(8,30)),
//                new CalendarDate(23,12,2021, new CalendarTime(10,30)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Bananes","Paris", "Je test desc", false);
//        CalendarEvent event9 = new CalendarEvent(
//                9,
//                new CalendarDate(22,12,2021, new CalendarTime(10,0)),
//                new CalendarDate(23,12,2021, new CalendarTime(10,30)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Kiwi","Paris", "Je test desc", false);
//        CalendarEvent event10 = new CalendarEvent(
//                10,
//                new CalendarDate(22,12,2021, new CalendarTime(18,0)),
//                new CalendarDate(23,12,2021, new CalendarTime(19,0)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Fraises","Paris", "Je test desc", false);
//        CalendarEvent event11 = new CalendarEvent(
//                11,
//                new CalendarDate(22,12,2021, new CalendarTime(17,30)),
//                new CalendarDate(23,12,2021, new CalendarTime(18,30)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Citron","Paris", "Je test desc", false);
//        CalendarEvent event12 = new CalendarEvent(
//                12,
//                new CalendarDate(22,12,2021, new CalendarTime(20,30)),
//                new CalendarDate(23,12,2021, new CalendarTime(21,30)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Melon","Paris", "Je test desc", false);
//        CalendarEvent event13 = new CalendarEvent(
//                13,
//                new CalendarDate(22,12,2021, new CalendarTime(20,30)),
//                new CalendarDate(23,12,2021, new CalendarTime(21,30)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Pommes","Paris", "Je test desc", false);
//        CalendarEvent event14 = new CalendarEvent(
//                14,
//                new CalendarDate(22,12,2021, new CalendarTime(14,0)),
//                new CalendarDate(23,12,2021, new CalendarTime(14,15)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Clementines","Paris", "Je test desc", false);
//        CalendarEvent event15 = new CalendarEvent(
//                15,
//                new CalendarDate(22,12,2021, new CalendarTime(14,0)),
//                new CalendarDate(23,12,2021, new CalendarTime(14,5)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Ananas","Paris", "Je test desc", false);
//        CalendarEvent event16 = new CalendarEvent(
//                16,
//                new CalendarDate(22,12,2021, new CalendarTime(7,30)),
//                new CalendarDate(23,12,2021, new CalendarTime(10,45)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Lichi","Paris", "Je test desc", false);
//        CalendarEvent event17 = new CalendarEvent(
//                17,
//                new CalendarDate(22,12,2021, new CalendarTime(7,30)),
//                new CalendarDate(23,12,2021, new CalendarTime(10,30)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Poire","Paris", "Je test desc", false);
//        calendarEvents.add(event);
//        calendarEvents.add(event2);
//        calendarEvents.add(event3);
//        calendarEvents.add(event4);
//        calendarEvents.add(event5);
//        calendarEvents.add(event6);
//        calendarEvents.add(event7);
//        calendarEvents.add(event8);
//        calendarEvents.add(event9);
//        calendarEvents.add(event10);
//        calendarEvents.add(event11);
//        calendarEvents.add(event12);
//        calendarEvents.add(event13);
//        calendarEvents.add(event14);
//        calendarEvents.add(event15);
//        calendarEvents.add(event16);
//        calendarEvents.add(event17);
    }

    public List<CalendarEvent> getEvents(){
        return List.copyOf(this.calendarEvents);
    }

//    public void removeEvent(int id){
//        for(int i = 0; i < this.calendarEvents.size(); i++){
//            CalendarEvent event = calendarEvents.get(i);
//            if(event.getId() == id){
//                this.calendarEvents.remove(i);
//            }
//        }
//    }

    public static void main(String[] args) {

    }

}
