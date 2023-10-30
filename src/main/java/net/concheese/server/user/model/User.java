package net.concheese.server.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.concheese.server.common.BaseEntity;
import net.concheese.server.info.model.Bookmark;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

  @Column(name = "login_id", nullable = false, unique = true)
  private String loginId;

  @Column(name = "social")
  private String social;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "nickname", nullable = false)
  private String nickname;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private UserRole userRole;

  @OneToMany
  @Column(name = "bookmarks")
  private List<Bookmark> bookmarks;

  public User(String loginId, String social, String name, String nickname, String email,
      UserRole userRole) {
    this.loginId = loginId;
    this.social = social;
    this.name = name;
    this.nickname = nickname;
    this.email = email;
    this.userRole = userRole;
  }

  public User update(User user) {
    this.name = user.getName();
    this.nickname = user.getNickname();
    this.email = user.getEmail();
    this.userRole = user.getUserRole();
    return this;
  }

}

