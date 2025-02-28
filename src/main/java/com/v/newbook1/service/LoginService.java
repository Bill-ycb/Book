package com.v.newbook1.service;

import com.v.newbook1.dto.UserLoginDTO;
import com.v.newbook1.model.User;
import com.v.newbook1.vo.UserLoginVO;

public interface LoginService {
    UserLoginVO wxLogin(UserLoginDTO userLoginDTO);

    User code(String code);
}
