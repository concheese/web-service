package net.concheese.server.user.controller;

import lombok.RequiredArgsConstructor;
import net.concheese.server.user.dto.UserForm;
import net.concheese.server.user.model.User;
import net.concheese.server.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserRestController {

  private final UserService userService;

  @PostMapping("/create")
  public User newUser(@RequestBody UserForm request) {
    return userService.saveOrUpdateUserForm(request);
  }
}
