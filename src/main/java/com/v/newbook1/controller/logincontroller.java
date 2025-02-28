package com.v.newbook1.controller;

import com.v.newbook1.dto.CodeDTO;
import com.v.newbook1.dto.UserLoginDTO;
import com.v.newbook1.model.User;
import com.v.newbook1.result.Result;
import com.v.newbook1.service.LoginService;
import com.v.newbook1.vo.UserLoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/book")
@CrossOrigin(origins ="*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class logincontroller {

    private final LoginService loginService;
    @PostMapping("/code")
    public Result<User> code(@RequestBody CodeDTO codeDTO){
        User user = loginService.code(codeDTO.getCode());
        return Result.success(user);
    }

    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户微信登录:{}", userLoginDTO);
        UserLoginVO userLoginVO = loginService.wxLogin(userLoginDTO);
        return Result.success(userLoginVO);
    }
}
