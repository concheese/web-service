package net.concheese.server.user.service;

import net.concheese.server.user.dto.UserForm;
import net.concheese.server.user.model.User;

public interface UserService {

  User saveOrUpdate(User user);

  User saveOrUpdateUserForm(UserForm userForm);
  
}
