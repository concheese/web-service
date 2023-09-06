package net.concheese.server.community.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import net.concheese.server.community.controller.PostRequest;
import net.concheese.server.community.model.Category;
import net.concheese.server.community.model.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class PostServiceTest {

  private final PostRequest postRecord = new PostRequest("Example Post", Category.FREE,
      "Hello, world!");
  private Post testPost;

  @Autowired
  private PostService postService;

  @Test
  @Order(1)
  @DisplayName("Create a post")
  public void testCreatePost() {
    testPost = postService.createPost(postRecord.title(), postRecord.category(),
        postRecord.content());
    assertNotNull(testPost);
  }

  @Test
  @Order(2)
  @DisplayName("Update a post")
  public void testUpdatePost() {
    testPost.setTitle("Example Updated Post");
    testPost.setContent("Farewell, old my friend.");
    testPost = postService.updatePost(testPost.getPostId(), testPost.getTitle(),
        testPost.getCategory(), testPost.getContent());
    assertNotNull(testPost);
  }

  @Test
  @Order(3)
  @DisplayName("Read a post")
  public void testReadPost() {
    Post requestedPost = postService.readPost(testPost.getPostId());
    assertEquals(testPost, requestedPost);
  }

  @Test
  @Order(4)
  @DisplayName("Delete a post")
  public void testDeletePost() {
    postService.deletePost(testPost.getPostId());
    assertNull(postService.readPost(testPost.getPostId()));
  }

}