package net.concheese.server.community.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import net.concheese.server.community.model.Category;
import net.concheese.server.community.model.Post;

public interface PostRepository {

  Post insert(Post post);

  Optional<Post> selectById(UUID postId);

  List<Post> selectByCategory(Category category);

  List<Post> selectAll();

  Post update(Post post);

  void deleteById(UUID postId);

}
