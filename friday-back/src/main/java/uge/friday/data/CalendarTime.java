package uge.friday.data;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;

@Entity
public class CalendarTime extends PanacheEntity {
    private int hour;
    private int minute;

    public CalendarTime(){
        this.hour = 12;
        this.minute = 30;
    }

    public CalendarTime(int hour, int minute){
        if(hour < 0 || hour > 23) throw new IllegalArgumentException("Hour can't be under 0 or above 23");
        if(minute < 0 || minute > 59) throw new IllegalArgumentException("Hour can't be under 0 or above 23");

        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public String toString() {
        return "CalendarTime{" +
                "hour=" + hour +
                ", minute=" + minute +
                '}';
    }
}
