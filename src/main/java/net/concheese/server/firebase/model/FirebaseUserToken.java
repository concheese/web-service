package net.concheese.server.firebase.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Tokens")
public class FirebaseUserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter @Setter
    private String token;
    @Getter @Setter
    private String userId;

}
