package net.concheese.server.info.converter;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.concheese.server.info.dto.UserInfoForm;
import net.concheese.server.info.model.Bookmark;
import net.concheese.server.info.repository.BookmarkRepository;
import net.concheese.server.user.model.User;
import net.concheese.server.user.repository.UserRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserInfoFormToUser implements Converter<UserInfoForm, User> {

  private final UserRepository userRepository;
  private final BookmarkRepository bookmarkRepository;

  @Override
  public User convert(UserInfoForm form) {
    User user = new User();
    if (form.id() != null) {
      user = userRepository.findById(form.id()).orElse(user);
    }
    if (form.name() != null) {
      user.setName(form.name());
    }
    if (form.bookmarks() != null) {
      List<Bookmark> bookmarks = user.getBookmarks();
      for (String bookmarkId : form.bookmarks()) {
        Bookmark bookmark = bookmarkRepository.findById(UUID.fromString(bookmarkId)).orElse(null);
        if (bookmark != null && !bookmarks.contains(bookmark)) {
          bookmarks.add(bookmark);
        }
      }
      user.setBookmarks(bookmarks);
    }
    return user;
  }
}
