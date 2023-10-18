package net.concheese.server.concert.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.UUID;

public class Schedules {
    private UUID ScheduleID;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd[ HH:mm:ss][.SSS][.SS][.S]")
    private LocalDate timestamp;
    private int postal;

    public Schedules() {
    }

    public Schedules(UUID ScheduleID, LocalDate timestamp, int postal) {
        this.ScheduleID = ScheduleID;
        this.timestamp = timestamp;
        this.postal = postal;
    }

    public Schedules(LocalDate timestamp, int postal) {
        this.timestamp = timestamp;
        this.postal = postal;
    }

    public UUID getScheduleID() {
        return ScheduleID;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public int getPostal() {
        return postal;
    }

    public Schedules(UUID ScheduleID) {
        this.ScheduleID = ScheduleID;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public void setPostal(int postal) {
        this.postal = postal;
    }
}
