package net.concheese.server.user.service;

import jakarta.transaction.Transactional;
import net.concheese.server.user.model.User;
import net.concheese.server.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User readInfo(User user) {
        if(user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        } else
            return user;
    }

    @Transactional
    public void deleteUser(String loginId) {
        userRepository.deleteUserByloginId(loginId);
    }

    @Transactional
    public User updateUser(String loginId, String nickname) {
        User user = userRepository.findByLoginId(loginId).orElse(null);

        if(user != null) {
            user.update(nickname);
            return userRepository.save(user);
        }

        return user;
    }
}
