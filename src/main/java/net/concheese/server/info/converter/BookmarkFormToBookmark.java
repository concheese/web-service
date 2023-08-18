package net.concheese.server.info.converter;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.concheese.server.info.dto.BookmarkForm;
import net.concheese.server.info.model.Bookmark;
import net.concheese.server.info.repository.BookmarkRepository;
import net.concheese.server.info.repository.ConcertRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookmarkFormToBookmark implements Converter<BookmarkForm, Bookmark> {

  private final BookmarkRepository bookmarkRepository;
  private final ConcertRepository concertRepository;

  @Override
  public Bookmark convert(BookmarkForm form) {
    Bookmark bookmark = new Bookmark();
    if (form.id() != null) {
      bookmark = bookmarkRepository.findById(form.id()).orElse(bookmark);
    }
    if (form.status() != null) {
      bookmark.setStatus(form.status());
    }
    if (form.liked() != null) {
      bookmark.setLiked(form.liked());
    }
    if (form.concertId() != null) {
      bookmark.setConcert(
          concertRepository.findById(UUID.fromString(form.concertId())).orElse(null));
    }
    return bookmark;
  }

}
