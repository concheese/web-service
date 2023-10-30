package net.concheese.server.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.concheese.server.user.model.User;
import net.concheese.server.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private static UserRepository userRepository;

  public User readInfo(User user) {
    if (user == null) {
      throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
    } else {
      return user;
    }
  }

  @Transactional
  public void deleteUser(String loginId) {
    userRepository.deleteByLoginId(loginId);
  }

  @Transactional
  public User updateUser(String loginId, String nickname) {
    User user = userRepository.findByLoginId(loginId).orElse(null);

    if (user != null) {
      user.setNickname(nickname);
      user = userRepository.save(user);
    }

    return user;
  }
}