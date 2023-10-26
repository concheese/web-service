package net.concheese.server.info.repository;

import java.util.UUID;
import net.concheese.server.info.model.Ticketing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@code Ticketing} 엔터티에 대한 CRUD 작업을 위한 리포지토리 인터페이스입니다.
 * <p>
 * 이 인터페이스는 Spring Data JPA가 제공하는 {@code JpaRepository} 인터페이스를 확장하며, {@code Ticketing} 엔터티에 대한 CRUD
 * 기능을 제공합니다. 주 키의 데이터 타입으로 UUID를 사용합니다.
 * </p>
 * <p>
 * {@code Repository} 주석은 이것이 Spring Data 리포지토리라는 것을 나타냅니다. 스프링은 자동으로 이 리포지토리 인터페이스의 구체적인 구현을 생성하며,
 * 일반적인 CRUD 작업에 대한 메서드를 제공합니다.
 * </p>
 *
 * @author Lynn Choi
 * @author MyoungHa Ji
 * @version 1.0
 * @see net.concheese.server.info.model.Ticketing
 * @since 2023-10-21
 */
@Repository
public interface TicketingRepository extends JpaRepository<Ticketing, UUID> {

}
