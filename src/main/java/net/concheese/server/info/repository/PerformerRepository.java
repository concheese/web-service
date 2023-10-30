package net.concheese.server.info.repository;

import java.util.Optional;
import java.util.UUID;
import net.concheese.server.info.model.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@code Performer} 엔터티에 대한 CRUD 작업을 위한 리포지토리 인터페이스입니다.
 * <p>
 * 이 인터페이스는 Spring Data JPA가 제공하는 {@code JpaRepository} 인터페이스를 확장하며, {@code Performer} 엔터티에 대한 CRUD
 * 및 이름으로 연주자를 검색하는 기능을 제공합니다. 주 키의 데이터 타입으로 UUID를 사용합니다.
 * </p>
 * <p>
 * {@code Repository} 주석은 이것이 Spring Data 리포지토리라는 것을 나타냅니다. 스프링은 자동으로 이 리포지토리 인터페이스의 구체적인 구현을 생성하며,
 * 일반적인 CRUD 작업에 대한 메서드를 제공합니다.
 * </p>
 *
 * @author Lynn Choi
 * @author MyoungHa Ji
 * @version 1.0
 * @see net.concheese.server.info.model.Performer
 * @since 2023-10-21
 */
@Repository
public interface PerformerRepository extends JpaRepository<Performer, UUID> {

  /**
   * 주어진 이름으로 연주자를 검색합니다.
   *
   * @param name 연주자의 이름
   * @return 해당 이름의 연주자, 존재하지 않는 경우는 {@code Optional}이 비어있을 것입니다.
   */
  Optional<Performer> findByName(String name);

}
