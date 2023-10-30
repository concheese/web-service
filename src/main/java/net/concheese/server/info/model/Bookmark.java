package net.concheese.server.info.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import net.concheese.server.common.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Table(name = "bookmarks")
public class Bookmark extends BaseEntity {

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private BookmarkStatus status = BookmarkStatus.VISITED;

  @Column(name = "liked")
  @Enumerated(EnumType.STRING)
  private BookmarkLike liked = BookmarkLike.NONE;

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

  @ManyToOne
  @JoinColumn(name = "concert_id", nullable = false)
  private Concert concert;

}
