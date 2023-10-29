package net.concheese.server.user.service;

import lombok.RequiredArgsConstructor;
import net.concheese.server.user.converter.UserFormToUser;
import net.concheese.server.user.dto.UserForm;
import net.concheese.server.user.model.User;
import net.concheese.server.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultUserService implements UserService {

  private final UserRepository userRepository;
  private final UserFormToUser userFormToUser;

  @Override
  public User saveOrUpdate(User user) {
    userRepository.save(user);
    return user;
  }

  @Override
  public User saveOrUpdateUserForm(UserForm userForm) {
    return saveOrUpdate(userFormToUser.convert(userForm));
  }
}
