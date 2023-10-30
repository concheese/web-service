package net.concheese.server.user.model;

import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OAuth2Attributes {

  private final Map<String, Object> attributes;
  private final String attributeKey;
  private final String loginId;
  private final String social;
  private final String name;
  private final String email;
  private final String nickname;
  private final UserRole userRole;

  public static OAuth2Attributes of(String social, String userNameAttributeName,
      Map<String, Object> attributes) {
    OAuth2Attributes oAuth2Attributes;
    if (social.equals(NaverUserAttributes.SOCIAL_NAME)) {
      oAuth2Attributes = ofNaver(userNameAttributeName, attributes);
    } else {
      oAuth2Attributes = ofDefault(social, userNameAttributeName, attributes);
    }
    return oAuth2Attributes;
  }

  private static OAuth2Attributes ofDefault(String social, String userNameAttributeName,
      Map<String, Object> attributes) {
    Map<String, Object> response = (Map<String, Object>) attributes.get(userNameAttributeName);
    return new OAuth2Attributes(attributes, userNameAttributeName, (String) response.get("id"),
        social, (String) response.get("name"), (String) response.get("email"),
        (String) response.get("nickname"), UserRole.ROLE_USER);
  }

  private static OAuth2Attributes ofNaver(String userNameAttributeName,
      Map<String, Object> attributes) {
    Map<String, Object> response = (Map<String, Object>) attributes.get(userNameAttributeName);
    return new NaverUserAttributes(attributes, userNameAttributeName, (String) response.get("id"),
        (String) response.get("name"), (String) response.get("email"),
        (String) response.get("nickname"), UserRole.ROLE_USER);
  }

  public User toEntity() {
    return new User(loginId, social, name, nickname, email, UserRole.ROLE_USER);
  }

}