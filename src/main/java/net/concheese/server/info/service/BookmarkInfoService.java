package net.concheese.server.info.service;

import java.util.UUID;
import net.concheese.server.info.dto.BookmarkForm;
import net.concheese.server.info.model.Bookmark;

public interface BookmarkInfoService {

  Bookmark saveOrUpdate(Bookmark bookmark);

  Bookmark saveOrUpdateBookmarkForm(BookmarkForm bookmarkForm);

  Bookmark getById(UUID uuid);

  void delete(UUID uuid);
}
