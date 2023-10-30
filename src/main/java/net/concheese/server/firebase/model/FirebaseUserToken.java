package net.concheese.server.firebase.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.concheese.server.common.BaseEntity;

@Getter
@Setter
@Entity
public class FirebaseUserToken extends BaseEntity {
    private String token;
    private String userId;
}
