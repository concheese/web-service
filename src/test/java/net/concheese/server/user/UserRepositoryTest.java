//package net.concheese.server.user;
//
//import jakarta.transaction.Transactional;
//import net.concheese.server.user.ex.User;
//import net.concheese.server.user.repository.UserRepository;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//
//@SpringBootTest
//public class UserRepositoryTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    @Transactional
//    public void createTest() {
//
//        User user = new User("test", "test@test", "test1", "test01");
//        userRepository.save(user);
//
//        userRepository.deleteUserBySnsId("test01");
//
//        // 삭제 후 해당 사용자를 찾아보면 null이 반환되어야 합니다.
//        Optional<User> deletedUserOptional = userRepository.findBySnsId("test01");
//        assertFalse(deletedUserOptional.isPresent(), "사용자가 삭제되지 않았습니다.");
//    }
//}
