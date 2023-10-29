//package net.concheese.server.user;
//
//import net.concheese.server.user.ex.UserDTO;
//import net.concheese.server.user.ex.User;
//import net.concheese.server.user.repository.UserRepository;
//import net.concheese.server.user.service.UserService;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//
//@SpringBootTest
//public class UserServiceTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;
//
//    @Test
//    public void createTest() {
//
//        UserDTO userDTO = new UserDTO("바보", "rr@naver.com", "rr", "rr1123");
//        UserDTO fakeUser = new UserDTO(userDTO.getUsername(), userDTO.getEmail(), userDTO.getNickname(), userDTO.getSnsId());
//
//        // createUser 메소드 호출
//        User createdUser = userService.createUser(fakeUser);
//
//        // 결과 확인
//        assertEquals(userDTO.getUsername(), createdUser.getUsername());
//        assertEquals(userDTO.getEmail(), createdUser.getEmail());
//        assertEquals(userDTO.getNickname(), createdUser.getNickname());
//        assertEquals(userDTO.getSnsId(), createdUser.getSnsId());
//    }
//}
