package net.concheese.server.info.service;

import java.util.List;
import java.util.UUID;
import net.concheese.server.info.dto.PostForm;
import net.concheese.server.info.model.Post;
import net.concheese.server.info.model.PostCategory;

public interface PostInfoService {

  Post saveOrUpdate(Post post);

  Post saveOrUpdatePostForm(PostForm postForm);

  List<Post> listAll();

  List<Post> listRecent(int count);

  List<Post> listAllByCategory(PostCategory category);

  List<Post> listAllByUserId(UUID uuid);

  List<Post> listAllByUserName(String userName);

  Post getById(UUID uuid);

  void delete(UUID uuid);

}
