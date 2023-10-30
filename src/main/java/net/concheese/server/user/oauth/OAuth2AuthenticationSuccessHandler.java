package net.concheese.server.user.oauth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import net.concheese.server.user.jwt.JwtTokenUtil;
import net.concheese.server.user.model.User;
import net.concheese.server.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  //로그인 성공시 호출
  @Autowired
  JwtTokenUtil jwtTokenUtil;
  @Autowired
  private UserRepository userRepository;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {

    log.debug("로그인 성공");
    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
    System.out.println(oAuth2User.getAttributes());
    String loginId = ((Map<String, Object>) oAuth2User.getAttributes().get("response")).get("id")
        .toString();

    User user = userRepository.findByLoginId(loginId).orElse(null);

    String jwtToken = jwtTokenUtil.generateToken(user);

    String url = UriComponentsBuilder.fromUriString("https://concheese.net/" + jwtToken)
        .build().toUriString();
    //클라이언트 리다이렉트 주소 -> 로그인 성공 후 페이지

    if (response.isCommitted()) {
      log.info("응답이 이미 커밋된 상태입니다. 리다이렉트 시킬 수 없습니다.");
      return;
    }
    getRedirectStrategy().sendRedirect(request, response, url);
  }
}
