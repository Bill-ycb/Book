package com.v.newbook1.service;

import com.v.newbook1.dto.UserUpdateDTO;
import com.v.newbook1.model.User;

public interface UserService {
    void updateUser(UserUpdateDTO updateDTO);

    User getUserById(long userId);
}
