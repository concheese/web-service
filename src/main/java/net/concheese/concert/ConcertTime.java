package net.concheese.concert;

import java.time.LocalDateTime;

public class ConcertTime {
    private final long id;
    private String location;
    private LocalDateTime DateTime;

    public long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDateTime() {
        return DateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        DateTime = dateTime;
    }

    public ConcertTime(long id, String location, LocalDateTime dateTime) {
        this.id = id;
        this.location = location;
        DateTime = dateTime;
    }


}
