package com.v.newbook1.mapper;

import com.v.newbook1.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);


    void insert(User user);
}
