package net.concheese.server.concert.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name="concert_performer")
public class Concert_Performer {
    @Id @Column(name = "id")
    private final UUID id;
    @Column(name = "concert_id")
    private UUID concertId;
    @Column(name = "performer_id")
    private UUID performerId;

    public Concert_Performer(UUID id, UUID concertId, UUID performerId) {
        this.id = id;
        this.concertId = concertId;
        this.performerId = performerId;
    }

    public Concert_Performer() {
        this.id = UUID.randomUUID();
    }
}
