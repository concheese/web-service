package net.concheese.server.user.oauth;

import lombok.RequiredArgsConstructor;
import net.concheese.server.user.model.OAuth2Attributes;
import net.concheese.server.user.model.User;
import net.concheese.server.user.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  /**
   * Spring Security, Oauth2를 사용할 때 해당 클래스를 통해 올바른 사용자 객체가 반환되면 로그인 성공 처리
   */
  private final UserRepository userRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = delegate.loadUser(userRequest);

    String social = userRequest.getClientRegistration().getRegistrationId();
    String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
        .getUserInfoEndpoint().getUserNameAttributeName();
    OAuth2Attributes attributes = OAuth2Attributes.of(social, userNameAttributeName,
        oAuth2User.getAttributes());
    User user = saveOrUpdate(attributes);
    return new PrincipalDetails(user, attributes);
  }

  private User saveOrUpdate(OAuth2Attributes attributes) {
    User user = userRepository.findByLoginId(attributes.getLoginId()).orElse(null);
    if (user == null) {
      user = userRepository.save(attributes.toEntity());
    } else {
      user = userRepository.save(user.update(attributes.toEntity()));
    }
    return user;
  }
}