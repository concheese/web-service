package net.concheese.server.community.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import net.concheese.server.community.model.Category;
import net.concheese.server.community.model.Post;
import net.concheese.server.community.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultPostService implements PostService {

  private final PostRepository postRepository;

  public DefaultPostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public Post createPost(String title, Category category, String content) {
    return postRepository.insert(new Post(UUID.randomUUID(), title, category, content));
  }

  @Override
  public Post updatePost(UUID postId, String title, Category category, String content) {
    Optional<Post> optionalExistingPost = postRepository.selectById(postId);
    if (optionalExistingPost.isEmpty()) {
      return null;
    }
    Post existingPost = optionalExistingPost.get();
    existingPost.setTitle(title);
    existingPost.setCategory(category);
    existingPost.setContent(content);
    return postRepository.update(existingPost);
  }

  @Override
  public Post readPost(UUID postId) {
    return postRepository.selectById(postId).orElse(null);
  }

  @Override
  public List<Post> readPosts(Category category) {
    return postRepository.selectByCategory(category);
  }

  @Override
  public List<Post> readAllPosts() {
    return postRepository.selectAll();
  }

  @Override
  public void deletePost(UUID postId) {
    postRepository.deleteById(postId);
  }

}
