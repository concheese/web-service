package net.concheese.server.info.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.concheese.server.info.converter.PostFormToPost;
import net.concheese.server.info.dto.PostForm;
import net.concheese.server.info.model.Post;
import net.concheese.server.info.model.PostCategory;
import net.concheese.server.info.repository.PostRepository;
import net.concheese.server.user.model.User;
import net.concheese.server.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultPostInfoService implements PostInfoService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;
  private final PostFormToPost postFormToPost;

  @Override
  public Post saveOrUpdate(Post post) {
    return postRepository.save(post);
  }

  @Override
  public Post saveOrUpdatePostForm(PostForm postForm) {
    return saveOrUpdate(Objects.requireNonNull(postFormToPost.convert(postForm)));
  }

  @Override
  public List<Post> listAll() {
    return postRepository.findAll();
  }

  @Override
  public List<Post> listRecent(int count) {
    List<Post> posts = listAll();
    posts.sort(Comparator.reverseOrder());
    return posts.stream().limit(count).toList();
  }

  @Override
  public List<Post> listAllByCategory(PostCategory category) {
    return postRepository.findAllByCategory(category);
  }

  @Override
  public List<Post> listAllByUserId(UUID uuid) {
    return Optional.ofNullable(userRepository.findById(uuid).orElse(null))
        .map(postRepository::findAllByUser).orElse(null);
  }

  @Override
  public List<Post> listAllByUserName(String userName) {
    List<User> users = userRepository.findByName(userName);
    return users.stream().flatMap(user -> listAllByUserId(user.getId()).stream())
        .collect(Collectors.toList());
  }

  @Override
  public Post getById(UUID uuid) {
    return postRepository.findById(uuid).orElse(null);
  }

  @Override
  public void delete(UUID uuid) {
    postRepository.deleteById(uuid);
  }

}
