package uge.friday.data;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class CalendarDate extends PanacheEntity {

    private int day;
    private int month;
    private int year;

    @ManyToOne(cascade = {CascadeType.ALL})
    private CalendarTime time;

    //Default constructor is needed for panache
    public CalendarDate(){
        this.day = 1;
        this.month = 1;
        this.year = 2021;
        this.time = null;
    }

    public CalendarDate(int day, int month, int year, CalendarTime time){
        //TODO check number of days in the month
        if(month < 1 || month > 12) throw new IllegalArgumentException("Month can't be under 1 or above 12");
        if(year < 0 || year > 4000) throw new IllegalArgumentException("Year must be between 1950 and 2100");
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