package net.concheese.server.community.service;

import java.util.List;
import java.util.UUID;
import net.concheese.server.community.model.Category;
import net.concheese.server.community.model.Post;

public interface PostService {

  Post createPost(String title, Category category, String content);

  Post updatePost(UUID boardId, String title, Category category, String content);

  Post readPost(UUID boardId);

  List<Post> readPosts(Category category);

  List<Post> readAllPosts();

  void deletePost(UUID boardId);

}
