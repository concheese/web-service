package net.concheese.server.concert.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name="schedule")
public class ScheduleTable {
    @Id
    @Column(name="id")
    private final UUID id;

    @Column(name="timestamp")
    private Timestamp time;
    @Column(name="postal")
    private int postal;
    @Column(name="concert_id")
    private UUID concertId;

    public ScheduleTable(UUID id, Timestamp time, int postal, UUID concertId) {
        this.id = id;
        this.time = time;
        this.postal = postal;
        this.concertId = concertId;
    }

    public ScheduleTable() {
        this.id = UUID.randomUUID();
    }
}
