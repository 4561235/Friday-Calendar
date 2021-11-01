package uge.friday.data;

public class CalendarTime {
    private final int hour;
    private final int minute;

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
