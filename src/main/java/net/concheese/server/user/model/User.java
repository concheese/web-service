package net.concheese.server.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.concheese.server.user.dto.OAuth2UserInfo;
import net.concheese.server.user.model.Role;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column
    private String social;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String loginId, String social, String password, String name, String nickname, String email, Role role) {
        this.loginId = loginId;
        this.social = social;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
    }

    public User(OAuth2UserInfo oAuth2UserInfo) {
        this.loginId = oAuth2UserInfo.getLoginId();
        this.social = oAuth2UserInfo.getSocial();
        this.name = oAuth2UserInfo.getName();
        this.nickname = oAuth2UserInfo.getNickname();
        this.email = oAuth2UserInfo.getEmail();
        this.role = Role.ROLE_USER;
    }

    public void update(String nickname) {
        this.nickname = nickname;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

}
