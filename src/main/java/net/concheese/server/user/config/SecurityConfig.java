package net.concheese.server.user.config;

import lombok.RequiredArgsConstructor;
import net.concheese.server.user.oauth.LogoutSuccessHandler;
import net.concheese.server.user.oauth.OAuth2AuthenticationSuccessHandler;
import net.concheese.server.user.oauth.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final LoginService loginService;

  private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

  private final LogoutSuccessHandler logoutSuccessHandler;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf
            .disable()
        )
        .cors(cors -> cors
            .configurationSource(corsConfigurationSource())
        )
        .authorizeHttpRequests(autorize -> autorize
            .anyRequest().permitAll()
        )
        .formLogin(formLogin -> formLogin
            .disable()
        )
        .httpBasic(httpBasic -> httpBasic
            .disable()
        )
        .oauth2Login(oauth2Login -> oauth2Login
            .defaultSuccessUrl("/login-success")
            .successHandler(oAuth2AuthenticationSuccessHandler)
            .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                .userService(loginService)
            )
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .permitAll()
            .logoutSuccessUrl("/")
            .logoutSuccessHandler(logoutSuccessHandler)
        );
    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedOriginPattern("*"); // 모든 도메인 허용
    configuration.addAllowedHeader("*"); // 모든 HTTP 헤더 허용
    configuration.addAllowedMethod("*"); // 모든 HTTP 메서드 허용
    configuration.setAllowCredentials(true); // 자격 증명 허용 설정

    configuration.addExposedHeader("Authorization");
    configuration.addExposedHeader("refreshToken");

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration); // 모든 경로에 대해 CORS 구성 적용

    return source;
  }
}
