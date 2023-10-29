package net.concheese.server.user.oauth;

import net.concheese.server.user.dto.NaverUserInfo;
import net.concheese.server.user.dto.OAuth2UserInfo;
import net.concheese.server.user.model.User;
import net.concheese.server.user.model.Role;
import net.concheese.server.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    /**
     * Spring Security, Oauth2를 사용할 때 해당 클래스를 통해 올바른 사용자 객체가 반환되면 로그인 성공 처리
     */

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PrincipalOauth2UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;
        String social = userRequest.getClientRegistration().getRegistrationId();

        if(social.equals("naver")) {
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        }
        // 다른 소셜 로그인 추가 시 구별

        String loginId = oAuth2UserInfo.getLoginId();
        String password = passwordEncoder.encode(loginId);
        String email = oAuth2UserInfo.getEmail();
        String nickname = oAuth2UserInfo.getNickname();
        String name = oAuth2UserInfo.getName();

        Role role = Role.ROLE_USER;

        User user = userRepository.findByLoginId(loginId).orElse(null);

        // loginId(고유 식별자 ID)가 DB에 존재하지 않는다면
        if(user == null) {
            //회원가입
            user = user.builder().loginId(loginId).social(social).password(password).name(name).nickname(nickname).email(email).role(role).build();
            userRepository.save(user); //DB에 user 저장
        }
        return new PrincipalDetails(user, oAuth2UserInfo);
    }
}
