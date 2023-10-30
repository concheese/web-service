package net.concheese.server.info.repository;

import java.util.List;
import java.util.UUID;
import net.concheese.server.info.model.Post;
import net.concheese.server.info.model.PostCategory;
import net.concheese.server.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, UUID> {

  List<Post> findAllByTitle(String title);

  List<Post> findAllByCategory(PostCategory category);

  List<Post> findAllByUser(User user);
  
}