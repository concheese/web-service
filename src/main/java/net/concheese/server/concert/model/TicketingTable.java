package net.concheese.server.concert.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ticketing")
public class TicketingTable {
    @Id @Column(name = "ticketing_id")
    private final UUID ticketingID;
    @Column(name = "start")
    private Timestamp start;
    @Column(name = "end")
    private Timestamp end;
    @Column(name = "status")
    private TicketingType status;
    @Column(name = "concert_id")
    private UUID concertId;

    public TicketingTable(UUID ticketingID, Timestamp start, Timestamp end, TicketingType status, UUID concertId) {
        this.ticketingID = ticketingID;
        this.start = start;
        this.end = end;
        this.status = status;
        this.concertId = concertId;
    }

    public TicketingTable() {
        this.ticketingID = UUID.randomUUID();
    }
}
