package net.concheese.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <strong>콘치즈</strong> 스프링 부트 애플리케이션의 진입점입니다.
 *
 * <p>이 클래스는 애플리케이션의 시작점으로 작용합니다. 스프링 부트 애플리케이션 컨텍스트를 초기화하고 설정하며, 애플리케이션을 실행시킵니다.
 * {@link #main(String[])} 메서드는 실행의 시작점입니다.
 *
 * @version 1.0
 * @since 2023-08-04
 */
@SpringBootApplication
@EnableJpaRepositories
public class BackendApplication {

  /**
   * 스프링 부트 애플리케이션을 실행하는 메인 메서드입니다.
   *
   * @param args 애플리케이션에 전달되는 명령줄 인수입니다.
   */
  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }

}
