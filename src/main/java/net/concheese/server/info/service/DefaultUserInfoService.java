package net.concheese.server.info.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.concheese.server.info.converter.UserInfoFormToUser;
import net.concheese.server.info.dto.UserInfoForm;
import net.concheese.server.user.model.User;
import net.concheese.server.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultUserInfoService implements UserInfoService {

  private final UserRepository userRepository;
  private final UserInfoFormToUser userInfoFormToUser;


  @Override
  public User saveOrUpdate(User user) {
    userRepository.save(user);
    return user;
  }

  @Override
  public User saveOrUpdateUserForm(UserInfoForm userInfoForm) {
    return saveOrUpdate(userInfoFormToUser.convert(userInfoForm));
  }

  @Override
  public User getById(UUID id) {
    return userRepository.findById(id).orElse(null);
  }
}
