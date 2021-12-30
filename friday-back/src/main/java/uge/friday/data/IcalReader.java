package uge.friday.data;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IcalReader {

    /**
     * @param icalString Formatted ical event
     * @return ical event transform into CalendarEvent
     */
    public List<CalendarEvent> readIcal(String icalString){
        ArrayList<CalendarEvent> list = new ArrayList<>();
        ICalendar ical = Biweekly.parse(icalString).first();
        List<VEvent> eventsList = ical.getEvents();

        for(VEvent vevent: eventsList) {

            Calendar calFrom = Calendar.getInstance();
            calFrom.setTime( vevent.getDateStart().getValue());
            Calendar calTo = Calendar.getInstance();
            calTo.setTime(vevent.getDateEnd().getValue());

            CalendarEventBuilder eventBuilder = new CalendarEventBuilder(vevent.getSummary().getValue(),
                    new CalendarDate(calFrom.get(Calendar.DAY_OF_MONTH),calFrom.get(Calendar.MONTH)+1,calFrom.get(Calendar.YEAR), new CalendarTime(calFrom.get(Calendar.HOUR),calFrom.get(Calendar.MINUTE))),
                    new CalendarDate(calTo.get(Calendar.DAY_OF_MONTH),calTo.get(Calendar.MONTH)+1,calTo.get(Calendar.YEAR), new CalendarTime(calTo.get(Calendar.HOUR),calTo.get(Calendar.MINUTE))));

            eventBuilder.setRecurrence(EventRecurrenceEnum.NONE)
                    .setCalendarType(CalendarTypeEnum.ICAL)
                    .setLocation(vevent.getLocation().getValue())
                    .setDescription(vevent.getDescription().getValue())
                    .setAllDay(false);

            list.add(eventBuilder.build());
        }
        return list;
    }
}
