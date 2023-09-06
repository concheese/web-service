package net.concheese.server.community.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import net.concheese.server.common.UpdatableModel;

@Getter
public class Post extends UpdatableModel {

  private final UUID postId;
  private final LocalDateTime createdAt;
  private String title;
  private Category category;
  private String content;

  public Post(UUID postId, String title, Category category, String content, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    super(updatedAt);
    this.postId = postId;
    this.title = title;
    this.category = category;
    this.content = content;
    this.createdAt = createdAt;
  }

  public Post(UUID postId, String title, Category category, String content,
      LocalDateTime createdAt) {
    this(postId, title, category, content, createdAt, createdAt);
  }

  public Post(UUID postId, String title, Category category, String content) {
    this(postId, title, category, content, LocalDateTime.now());
  }

  public void setTitle(String title) {
    this.title = title;
    update();
  }

  public void setCategory(Category category) {
    this.category = category;
    update();
  }

  public void setContent(String content) {
    this.content = content;
    update();
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    Post otherPost = (Post) object;
    return Objects.equals(postId, otherPost.postId) && Objects.equals(title, otherPost.title)
        && Objects.equals(category, otherPost.category) && Objects.equals(content,
        otherPost.content);
  }

}
