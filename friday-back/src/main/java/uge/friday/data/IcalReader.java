package uge.friday.data;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.DateEnd;
import biweekly.property.DateStart;
import biweekly.property.Location;

import java.util.Calendar;

public class IcalReader {

    public static void main(String[] args) {
        String str =
                "BEGIN:VCALENDAR\r\n" +
                        "VERSION:2.0\r\n" +
                        "PRODID:-//Microsoft Corporation//Outlook 14.0 MIMEDIR//EN\r\n" +
                        "BEGIN:VEVENT\r\n" +
                        "UID:0123\r\n" +
                        "DTSTAMP:20130601T080000Z\r\n" +
                        "SUMMARY;LANGUAGE=en-us:Team Meeting\r\n" +
                        "DTSTART:20130610T120000Z\r\n" +
                        "DURATION:PT1H\r\n" +
                        "RRULE:FREQ=WEEKLY;INTERVAL=2\r\n" +
                        "END:VEVENT\r\n" +
                        "END:VCALENDAR\r\n";



        ICalendar ical = Biweekly.parse(str).first();

        VEvent event = ical.getEvents().get(0);

        DateStart start = event.getDateStart();
        DateEnd end = event.getDateEnd();

//        System.out.println(event.getDescription().getValue());
//        System.out.println(event.getSummary().getValue());
//        Location location = event.getLocation();


//        System.out.println(location.getValue());
//        System.out.println(start.getValue());
//        System.out.println(end.getValue());
//        if(end == null){
//            System.out.println("End is null");
//        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(start.getValue());
        cal.setTime(start.getValue());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        System.out.println(day +" " +month +" " +year);

    }
}
