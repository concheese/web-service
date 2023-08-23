package net.concheese.concert;

import java.time.LocalDateTime;

public class ConcertTime {
    private final Long id;
    private String location;
    private LocalDateTime dateTime;

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ConcertTime(Long id, String location, LocalDateTime dateTime) {
        this.id = id;
        this.location = location;
        this.dateTime = dateTime;
    }

}
