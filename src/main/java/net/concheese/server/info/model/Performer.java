package net.concheese.server.info.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import net.concheese.server.common.BaseEntity;

/**
 * concheese 어플리케이션에서 이벤트 동안 공연하는 아티스트, 가수, 작가, 배우 등의 수행자를 나타내는 엔터티입니다.
 *
 * @author Lynn Choi
 * @author MyoungHa Ji
 * @version 1.0
 * @since 2023-10-21
 */
@Entity
public class Performer extends BaseEntity {

  /**
   * 수행자의 이름을 나타냅니다.
   */
  @Column(name = "name")
  private String name;

}
