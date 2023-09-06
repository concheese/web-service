package net.concheese.server.common;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class UpdatableModel {

  private LocalDateTime updatedAt;

  public UpdatableModel(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void update() {
    this.updatedAt = LocalDateTime.now();
  }

}
