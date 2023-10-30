package net.concheese.server.user.model;

import java.util.Map;
import lombok.Getter;

@Getter
public class NaverUserAttributes extends OAuth2Attributes {

  public static String SOCIAL_NAME = "naver";

  public NaverUserAttributes(Map<String, Object> attributes, String attributeKey,
      String loginId, String name, String email, String nickname, UserRole userRole) {
    super(attributes, attributeKey, loginId, SOCIAL_NAME, name, email, nickname, userRole);
  }

}