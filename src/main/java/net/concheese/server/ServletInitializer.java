package net.concheese.server;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 'concheese-backend' 스프링 부트 애플리케이션을 'war' 파일로 패키징하기 위한 초기화 클래스입니다.
 *
 * <p>이 클래스는 'war' 파일 형식으로 애플리케이션을 배포할 때 필요한 설정을 정의합니다.
 * {@link #configure(SpringApplicationBuilder)} 메서드는 'war' 파일 형식으로 배포할 때 애플리케이션을 설정하는 역할을 합니다.
 *
 * @version 1.0
 * @since 2023-08-04
 */
public class ServletInitializer extends SpringBootServletInitializer {

  /**
   * 'war' 파일로 배포할 때 애플리케이션을 설정하는 메서드입니다.
   *
   * @param application 스프링 애플리케이션 빌더 객체입니다.
   * @return 설정된 스프링 애플리케이션 빌더 객체를 반환합니다.
   */
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(BackendApplication.class);
  }

}
