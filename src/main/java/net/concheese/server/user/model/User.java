package net.concheese.server.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import net.concheese.server.common.BaseEntity;
import net.concheese.server.info.model.Bookmark;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

  @Column(name = "naver_id", nullable = false)
  private String naverId;

  @Column(name = "role", nullable = false)
  @Enumerated(EnumType.STRING)
  private UserRole role;

  @Column(name = "name", nullable = false)
  private String name;

  @OneToMany
  @Column(name = "bookmarks")
  private List<Bookmark> bookmarks;

}
