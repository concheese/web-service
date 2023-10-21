package net.concheese.server.concert.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="performers_concerts")
public class PerformerConcert {
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="performer_id")
    private Performer performer;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="concert_id")
    private Concert concert;
}
