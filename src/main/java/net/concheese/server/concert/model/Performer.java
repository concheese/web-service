package net.concheese.server.concert.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
public class Performer {
    @Getter @Column(name="id") @Id
    private final UUID id;
    @Getter @Setter @Column(name="name")
    private String name;

    public Performer(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Performer() {
        this.id = UUID.randomUUID();
    }
}
