package net.concheese.server.info.repository;

import java.util.UUID;
import net.concheese.server.info.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface BookmarkRepository extends JpaRepository<Bookmark, UUID> {

}
