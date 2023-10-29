package net.concheese.server.common;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;
import lombok.Data;

/**
 * concheese 어플리케이션의 기본 엔터티를 나타냅니다.
 * <p>
 * 이 클래스는 어플리케이션 내의 다른 엔터티 클래스들의 상위 클래스로써 기능합니다. 각 엔터티의 고유한 식별자를 캡슐화하여 전체 어플리케이션에서 ID 관리에 일관된 접근 방식을
 * 보장합니다.
 * </p>
 * <p>
 * {@code @MappedSuperclass} 주석은 이 클래스가 엔터티들 간에 공유되는 기본 클래스로 의도되었으며 그 자체로는 엔터티가 아님을 나타냅니다. 따라서 이
 * 클래스에 대한 테이블은 생성되지 않습니다.
 * </p>
 * <p>
 * Lombok의 {@code @Data} 주석은 클래스의 필드를 기반으로 일반적인 getter, setter, equals, hashcode 및 toString 메서드를
 * 생성합니다.
 * </p>
 *
 * @author MyoungHa Ji
 * @version 1.0
 * @see java.util.UUID
 * @since 2023-10-25
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

  /**
   * 각 엔터티의 고유 식별자입니다. UUID 전략을 사용하여 자동으로 생성됩니다.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

}
