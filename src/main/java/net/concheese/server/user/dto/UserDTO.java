package net.concheese.server.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.concheese.server.user.model.UserRole;

@Getter
@Setter
@Builder
public class UserDTO {
  String loginId;
  String social;
  String name;
  String email;
  String nickname;
  UserRole userRole;
}
