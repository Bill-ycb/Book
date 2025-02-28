package com.v.newbook1.service.Imp;


import com.v.newbook1.context.BaseContext;
import com.v.newbook1.dto.UserUpdateDTO;
import com.v.newbook1.mapper.UserMapper;
import com.v.newbook1.model.User;
import com.v.newbook1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    /**
     * 用户修改个人信息
     * @param updateDTO
     */
    @Override
    public void updateUser(UserUpdateDTO updateDTO) {
        long id = BaseContext.getCurrentId();
        User user =new User();
        user.setUserId(id);
        BeanUtils.copyProperties(updateDTO,user);
        userMapper.update(user);
    }

    @Override
    public User getUserById(long userId) {
        return userMapper.getUserById(userId);
    }
}
