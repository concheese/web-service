package net.concheese.server.user.converter;

import lombok.RequiredArgsConstructor;
import net.concheese.server.user.dto.UserForm;
import net.concheese.server.user.model.User;
import net.concheese.server.user.model.UserRole;
import net.concheese.server.user.repository.UserRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFormToUser implements Converter<UserForm, User> {

  private final UserRepository userRepository;

  @Override
  public User convert(UserForm form) {
    User user = new User();
    if (form.id() != null) {
      user = userRepository.findById(form.id()).orElse(user);
    }
    user.setNaverId(form.naverId());
    user.setRole(UserRole.USER);
    user.setName(form.name());
    return user;
  }
}
