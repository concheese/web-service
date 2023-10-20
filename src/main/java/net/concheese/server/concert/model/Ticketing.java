package net.concheese.server.concert.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="ticketings")
public class Ticketing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name="concert_id")
    private Concert concert;

    @Getter @Setter
    private LocalDateTime start;
    @Getter @Setter
    private LocalDateTime end;
    @Getter @Setter
    private TicketingType status;
}
