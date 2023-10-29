package net.concheese.server.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
  GUEST("ROLE_GUEST", "손님"),
  USER("ROLE_USER", "사용자"),
  ADMIN("ROLE_ADMIN", "관리자");
  private final String key;
  private final String value;

}
