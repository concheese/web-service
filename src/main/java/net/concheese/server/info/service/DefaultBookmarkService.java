package net.concheese.server.info.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.concheese.server.info.converter.BookmarkFormToBookmark;
import net.concheese.server.info.dto.BookmarkForm;
import net.concheese.server.info.model.Bookmark;
import net.concheese.server.info.repository.BookmarkRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultBookmarkService implements BookmarkInfoService {

  private final BookmarkRepository bookmarkRepository;
  private final BookmarkFormToBookmark bookmarkFormToBookmark;

  @Override
  public Bookmark saveOrUpdate(Bookmark bookmark) {
    bookmarkRepository.save(bookmark);
    return bookmark;
  }

  @Override
  public Bookmark saveOrUpdateBookmarkForm(BookmarkForm bookmarkForm) {
    return saveOrUpdate(bookmarkFormToBookmark.convert(bookmarkForm));
  }

  @Override
  public Bookmark getById(UUID id) {
    return bookmarkRepository.findById(id).orElse(null);
  }

  @Override
  public void delete(UUID id) {
    bookmarkRepository.deleteById(id);
  }
}
