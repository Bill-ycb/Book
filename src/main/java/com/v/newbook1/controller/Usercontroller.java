package com.v.newbook1.controller;

import com.v.newbook1.dto.UserUpdateDTO;
import com.v.newbook1.model.User;
import com.v.newbook1.result.Result;
import com.v.newbook1.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins ="*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/book/user")
public class Usercontroller {
    private final UserService userService;

    /**
     * 用户修改个人信息
     *
     * @param updateDTO
     * @return
     */
    @PostMapping("/update")
    public Result<UserUpdateDTO> updateUser(@RequestBody UserUpdateDTO updateDTO) {
        log.info("updateUser: {}", updateDTO);
        userService.updateUser(updateDTO);
        return Result.success(updateDTO);
    }

    /**
     * 用户详情页
     * @param userId
     * @return
     */
    @GetMapping("/get/{userId}")
    public Result<User> getUserById(@PathVariable long userId) {
        log.info("getUserByID: {}", userId);
        User user = userService.getUserById(userId);
        return Result.success(user);
    }
}
