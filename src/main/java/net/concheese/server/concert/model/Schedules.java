package net.concheese.server.concert.model;

import java.time.LocalDate;
import java.util.UUID;

public class Schedules {
    private final UUID schduleID;
    private LocalDate timestamp;
    private int postal;

    public Schedules(UUID schduleID, LocalDate timestamp, int postal) {
        this.schduleID = schduleID;
        this.timestamp = timestamp;
        this.postal = postal;
    }

    public UUID getSchduleID() {
        return schduleID;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public int getPostal() {
        return postal;
    }

    public Schedules(UUID schduleID) {
        this.schduleID = schduleID;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public void setPostal(int postal) {
        this.postal = postal;
    }
}
