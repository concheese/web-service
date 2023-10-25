package net.concheese.server.info.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
@Entity
@Table(name="schedules")
public class Schedule implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter @Setter
    private Timestamp timestamp;
    @Getter @Setter
    private int postal;
    @ManyToOne
    @JoinColumn(name="concert_id")
    private Concert concert;
}
