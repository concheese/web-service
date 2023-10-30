package net.concheese.server.info.converter;

import lombok.RequiredArgsConstructor;
import net.concheese.server.info.dto.PostForm;
import net.concheese.server.info.model.Post;
import net.concheese.server.info.repository.PostRepository;
import net.concheese.server.user.repository.UserRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostFormToPost implements Converter<PostForm, Post> {

  private final PostRepository postRepository;
  private final UserRepository userRepository;

  @Override
  public Post convert(PostForm form) {
    Post post = postRepository.findById(form.id()).orElse(new Post());
    if (post.getUser() == null) {
      post.setUser(userRepository.findById(form.userId()).orElse(null));
    }
    post.setTitle(form.title());
    post.setCategory(form.category());
    post.setContent(form.content());
    return post;
  }

}
