package net.concheese.server.user.dto;

import java.util.Map;

public interface OAuth2UserInfo {
  Map<String, Object> getAttributes();
  String getLoginId();
  String getSocial();
  String getName();
  String getEmail();
  String getNickname();

}