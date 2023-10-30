package net.concheese.server.user.model;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuth2Attributes {

  private final Map<String, Object> attributes;
  private final String attributeKey;
  private final String loginId;
  private final String social;
  private final String name;
  private final String email;
  private final String nickname;
  private UserRole userRole;

  public OAuth2Attributes(Map<String, Object> attributes, String attributeKey, String loginId,
      String social, String name, String email, String nickname, UserRole userRole) {
    this.attributes = attributes;
    this.attributeKey = attributeKey;
    this.loginId = loginId;
    this.social = social;
    this.name = name;
    this.email = email;
    this.nickname = nickname;
    this.userRole = userRole;
  }

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
    return OAuth2Attributes.builder().attributes(attributes).attributeKey(userNameAttributeName)
        .loginId((String) response.get("id")).social(social).name((String) response.get("name"))
        .email((String) response.get("email")).email((String) response.get("nickname"))
        .userRole(UserRole.ROLE_USER).build();
  }

  private static OAuth2Attributes ofNaver(String userNameAttributeName,
      Map<String, Object> attributes) {
    Map<String, Object> response = (Map<String, Object>) attributes.get(userNameAttributeName);
    return NaverUserAttributes.builder().attributes(attributes).attributeKey(userNameAttributeName)
        .loginId((String) response.get("id")).name((String) response.get("name"))
        .email((String) response.get("email")).email((String) response.get("nickname"))
        .userRole(UserRole.ROLE_USER).build();
  }

  public User toEntity() {
    return new User(loginId, social, name, nickname, email, UserRole.ROLE_USER);
  }

}