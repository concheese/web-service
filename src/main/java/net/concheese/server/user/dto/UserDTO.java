package net.concheese.server.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.concheese.server.user.model.Role;

@Getter
@Setter
@Builder
public class UserDTO {
    String loginId;
    String social;
    String name;
    String email;
    String nickname;
    Role role;
}
