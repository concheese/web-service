package net.concheese.server.user.dto;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo {
  private Map<String, Object> attributes;
  private Map<String, Object> attributesResponse;

  public NaverUserInfo(Map<String, Object> attributes) {
    this.attributes = (Map<String, Object>) attributes.get("response");
    this.attributesResponse = (Map<String, Object>) attributes.get("response");
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  @Override
  public String getLoginId() {
    return attributesResponse.get("id").toString();
  }

  @Override
  public String getSocial() {
    return "naver";
  }

  @Override
  public String getEmail() {
    return attributesResponse.get("email").toString();
  }

  @Override
  public String getName() {
    return attributesResponse.get("name").toString();
  }

  @Override
  public String getNickname() {
    return attributesResponse.get("nickname").toString();
  }
}