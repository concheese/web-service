package net.concheese.server.community.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.concheese.server.community.model.Category;
import net.concheese.server.community.model.Post;
import net.concheese.server.community.service.PostService;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/api/v1/community")
@RequiredArgsConstructor
public class PostRestController {

  private final PostService postService;

  @PostMapping("/post")
  public ResponseEntity<Post> createPost(@RequestBody PostRequest request) {
    return ResponseEntity.ok(
        postService.createPost(request.title(), request.category(), request.content()));
  }

  @PutMapping("/post/{postId}")
  public ResponseEntity<Post> updatePost(@RequestBody PostRequest request,
      @PathVariable String postId) {
    return ResponseEntity.ok(
        postService.updatePost(UUID.fromString(postId), request.title(), request.category(),
            request.content()));
  }

  @GetMapping("/post/{postId}")
  public Post readPost(@PathVariable String postId) {
    return postService.readPost(UUID.fromString(postId));
  }

  @GetMapping("/posts")
  public ResponseEntity<List<Post>> readPosts(@RequestParam Optional<Category> category) {
    return ResponseEntity.ok(category.map(postService::readPosts)
        .orElse(postService.readAllPosts()));
  }

  @DeleteMapping("/post/{postId}")
  public ResponseEntity<Void> deletePost(@PathVariable String postId) {
    postService.deletePost(UUID.fromString(postId));
    return ResponseEntity.ok().build();
  }

}
