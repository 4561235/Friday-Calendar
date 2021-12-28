package uge.friday.data;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.DateEnd;
import biweekly.property.DateStart;
import biweekly.property.Location;
import biweekly.property.RecurrenceRule;
import biweekly.util.Recurrence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IcalReader {


    public IcalReader(){

    }

    public List<CalendarEvent> readIcal(String icalString){
        ArrayList<CalendarEvent> list = new ArrayList<>();

        ICalendar ical = Biweekly.parse(icalString).first();
        List<VEvent> eventsList = ical.getEvents();
        for(VEvent vevent: eventsList) {
            DateStart start = vevent.getDateStart();
            DateEnd end = vevent.getDateEnd();
            String description = vevent.getDescription().getValue();
            //Recurrence rule = vevent.getRecurrenceRule().getValue();
            String summary = vevent.getSummary().getValue();
            String location = vevent.getLocation().getValue();


            Calendar calFrom = Calendar.getInstance();
            calFrom.setTime(start.getValue());
            Calendar calTo = Calendar.getInstance();
            calTo.setTime(end.getValue());


            CalendarEvent calEvent = new CalendarEvent(
                new CalendarDate(calFrom.get(Calendar.DAY_OF_MONTH),calFrom.get(Calendar.MONTH)+1,calFrom.get(Calendar.YEAR), new CalendarTime(calFrom.get(Calendar.HOUR),calFrom.get(Calendar.MINUTE))),
                new CalendarDate(calTo.get(Calendar.DAY_OF_MONTH),calTo.get(Calendar.MONTH)+1,calTo.get(Calendar.YEAR), new CalendarTime(calTo.get(Calendar.HOUR),calTo.get(Calendar.MINUTE))),
                EventRecurrenceEnum.NONE,
                CalendarTypeEnum.ICAL,
                    summary,location, description, false);

            list.add(calEvent);
        }
        return list;
    }
}
