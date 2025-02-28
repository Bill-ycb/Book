package com.v.newbook1.mapper;


import com.v.newbook1.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    void update(User user);

    @Select("select user_id, openid, id_number, head, create_time, connect, name from " +
            "user where user_id=#{userId}")
    User getUserById(long userId);
}
