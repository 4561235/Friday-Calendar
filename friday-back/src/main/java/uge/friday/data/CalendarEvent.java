package uge.friday.data;

import java.util.Objects;

public class CalendarEvent {
    private final CalendarDate from;
    private final CalendarDate to;
    private final String description;

    public CalendarEvent(CalendarDate from, CalendarDate to, String description){
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        Objects.requireNonNull(description);

        this.from = from;
        this.to = to;
        this.description = description;
    }

    @Override
    public String toString() {
        return "CalendarEvent{" +
                "from=" + from +
                ", to=" + to +
                ", description='" + description + '\'' +
                '}';
    }
}