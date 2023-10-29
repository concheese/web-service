package net.concheese.server.info.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.concheese.server.info.dto.UserInfoForm;
import net.concheese.server.info.service.UserInfoService;
import net.concheese.server.user.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/info/user")
public class UserInfoRestController {

  private final UserInfoService userInfoService;

  @PutMapping("")
  public User updateUser(@RequestBody UserInfoForm request) {
    return userInfoService.saveOrUpdateUserForm(request);
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable String id) {
    return userInfoService.getById(UUID.fromString(id));
  }

}
