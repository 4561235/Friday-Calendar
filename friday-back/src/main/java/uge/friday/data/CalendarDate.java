package uge.friday.data;

public class CalendarDate {

    private final int day;
    private final int month;
    private final int year;

    public CalendarDate(int day, int month, int year){
        //TODO check number of days in the month
        if(month < 1 || month > 12) throw new IllegalArgumentException("Month can't be under 1 or above 12");
        if(year < 1950 || year > 2100) throw new IllegalArgumentException("Year must be between 1950 and 2100");

        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return "CalendarDate{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    public static void main(String[] args) {
        System.out.println("OK");
    }
}