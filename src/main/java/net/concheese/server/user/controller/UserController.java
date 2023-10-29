package net.concheese.server.user.controller;

import net.concheese.server.user.model.User;
import net.concheese.server.user.repository.UserRepository;
import net.concheese.server.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/info/{loginId}")
    public ResponseEntity<User> readInfo(@PathVariable String loginId) {
        User user = userRepository.findByLoginId(loginId).orElse(null);
        if(user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userService.readInfo(user));
        }
    }

    @DeleteMapping("/{loginId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String loginId) {
        Optional<User> userOptional = userRepository.findByLoginId(loginId);
        if (userOptional.isPresent()) {
            userService.deleteUser(loginId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/info/{loginId}")
    public ResponseEntity<User> updateUser(@PathVariable String loginId,
                                           @RequestParam String nickname) {
        User user = userRepository.findByLoginId(loginId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        if (nickname == null || nickname.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(userService.updateUser(loginId, nickname));
    }
}