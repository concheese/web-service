package net.concheese.server.info.service;

import java.util.UUID;
import net.concheese.server.info.dto.UserInfoForm;
import net.concheese.server.user.model.User;

public interface UserInfoService {

  User saveOrUpdate(User convert);

  User saveOrUpdateUserForm(UserInfoForm userInfoForm);

  User getById(UUID id);

}
