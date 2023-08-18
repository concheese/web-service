package net.concheese.server.info.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import net.concheese.server.common.BaseEntity;
import net.concheese.server.user.model.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post extends BaseEntity implements Comparable<Post> {

  @ManyToOne
  @JoinColumn(name = "user")
  private User user;

  @Column(name = "title")
  private String title;

  @Column(name = "category")
  @Enumerated(EnumType.STRING)
  private PostCategory category;

  @Lob
  @Column(name = "content")
  private String content;

  @Column(name = "created")
  @CreationTimestamp
  @Schema(example = "1900-01-01 00:00:00", type = "string")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime created;

  @Column(name = "updated")
  @UpdateTimestamp
  @Schema(example = "1900-01-01 00:00:00", type = "string")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updated;

  @Override
  public int compareTo(@NonNull Post post) {
    if (getCreated() == null || post.getCreated() == null) {
      return 0;
    }
    return getCreated().compareTo(post.getCreated());
  }

}
