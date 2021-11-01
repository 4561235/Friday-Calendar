package uge.friday.data;

import java.util.Objects;

public class CalendarDate {

    private final int day;
    private final int month;
    private final int year;
    private final CalendarTime time;

    public CalendarDate(int day, int month, int year, CalendarTime time){
        //TODO check number of days in the month
        if(month < 1 || month > 12) throw new IllegalArgumentException("Month can't be under 1 or above 12");
        if(year < 1950 || year > 2100) throw new IllegalArgumentException("Year must be between 1950 and 2100");
        Objects.requireNonNull(time);

        this.day = day;
        this.month = month;
        this.year = year;
        this.time = time;
    }

    public int getDay(){
        return this.day;
    }

    public int getMonth(){
        return this.month;
    }

    public int getYear(){
        return this.year;
    }

    public CalendarTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "CalendarDate{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", time=" + time +
                '}';
    }
}