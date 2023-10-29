package net.concheese.server.concert.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="schedules")
public class Schedule implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter @Setter
    private LocalDateTime timestamp;
    @Getter @Setter
    private int postal;
    @ManyToOne
    @JoinColumn(name="concert_id")
    private Concert concert;
}
