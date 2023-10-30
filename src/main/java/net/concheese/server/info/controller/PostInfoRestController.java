package net.concheese.server.info.controller;

import java.util.List;
import java.util.UUID;
import net.concheese.server.info.dto.PostForm;
import net.concheese.server.info.model.Post;
import net.concheese.server.info.model.PostCategory;
import net.concheese.server.info.service.PostInfoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/info/post")
public class PostInfoRestController {

  private final PostInfoService postInfoService;

  public PostInfoRestController(PostInfoService postInfoService) {
    this.postInfoService = postInfoService;
  }

  @PostMapping("/create")
  public Post newPost(@RequestBody PostForm request) {
    return postInfoService.saveOrUpdatePostForm(request);
  }

  @PutMapping("/update")
  public Post updatePost(@RequestBody PostForm request) {
    return postInfoService.saveOrUpdatePostForm(request);
  }

  @GetMapping("/posts")
  public List<Post> listPosts(@RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "category", required = false) String category,
      @RequestParam(value = "userId", required = false) String userId,
      @RequestParam(value = "username", required = false) String userName,
      @RequestParam(value = "content", required = false) String content) {
    List<Post> posts = postInfoService.listAll();
    if (category != null) {
      posts.retainAll(postInfoService.listAllByCategory(PostCategory.valueOf(category)));
    }
    if (userId != null) {
      posts.retainAll(postInfoService.listAllByUserId(UUID.fromString(userId)));
    } else if (userName != null) {
      posts.removeIf(post -> !post.getUser().getName().contains(userName));
    }
    if (title != null) {
      posts.removeIf(post -> !post.getTitle().contains(title));
    }
    if (content != null) {
      posts.removeIf(post -> !post.getContent().contains(content));
    }
    return posts;
  }

  @GetMapping("/{id}")
  public Post getPost(@PathVariable String id) {
    return postInfoService.getById(UUID.fromString(id));
  }


  @DeleteMapping("/delete/{id}")
  public void deletePost(@PathVariable String id) {
    postInfoService.delete(UUID.fromString(id));
  }

}
