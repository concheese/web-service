package net.concheese.server.info.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.concheese.server.info.dto.BookmarkForm;
import net.concheese.server.info.model.Bookmark;
import net.concheese.server.info.service.BookmarkInfoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/info/bookmark")
public class BookmarkInfoRestController {

  private final BookmarkInfoService bookmarkInfoService;

  @PostMapping("")
  public Bookmark newBookmark(@RequestBody BookmarkForm request) {
    return bookmarkInfoService.saveOrUpdateBookmarkForm(request);
  }

  @PutMapping("")
  public Bookmark updateBookmark(@RequestBody BookmarkForm request) {
    return bookmarkInfoService.saveOrUpdateBookmarkForm(request);
  }

  @GetMapping("/{id}")
  public Bookmark getBookmark(@PathVariable String id) {
    return bookmarkInfoService.getById(UUID.fromString(id));
  }

  @DeleteMapping("/{id}")
  public void deleteBookmark(@PathVariable String id) {
    bookmarkInfoService.delete(UUID.fromString(id));
  }

}
