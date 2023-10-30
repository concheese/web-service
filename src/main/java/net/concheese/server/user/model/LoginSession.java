package net.concheese.server.user.model;

import java.io.Serializable;
import lombok.Getter;

@Getter
public class LoginSession implements Serializable {

  private final String id;

  public LoginSession(User user) {
    this.id = String.valueOf(user.getId());
  }

}
