package com.v.newbook1.service.Imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.v.newbook1.dto.UserLoginDTO;
import com.v.newbook1.exception.AppException;
import com.v.newbook1.exception.AppExceptionCodeMsg;
import com.v.newbook1.mapper.LoginMapper;
import com.v.newbook1.model.User;
import com.v.newbook1.properities.WechatProperties;
import com.v.newbook1.service.LoginService;
import com.v.newbook1.utils.HttpClientUtils;
import com.v.newbook1.utils.JwtUtils;
import com.v.newbook1.vo.UserLoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginServiceImp implements LoginService {
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    private final LoginMapper loginMapper;

    private final WechatProperties weChatProperties;
    @Override
    public UserLoginVO wxLogin(UserLoginDTO userLoginDTO) {
        //调用微信接口获得当前用户的openid
        String openid=getOpenid(userLoginDTO.getCode());
        //判断openid是否为空，若为空则表示失败，返回登陆异常
        //if(openid==null){
        //    throw new AppException(AppExceptionCodeMsg.OPEN_ID_NULL);
        //}
        //判断当前用户是否为新用户
        User user=loginMapper.getByOpenid(openid);
        if(user==null){
            user= User.builder()
                    .openid(openid)
                    .name(userLoginDTO.getUserName())
                    .idNumber(userLoginDTO.getIdNumber())
                    .head(userLoginDTO.getHead())
                    .connect(userLoginDTO.getConnect())
                    .createTime(LocalDateTime.now())
                    .build();
            //如果是新用户自动注册
            loginMapper.insert(user);
        }
        Map<String, Object> Claims = new HashMap<>();
        Claims.put("userId", user.getUserId());
        String jwt = JwtUtils.getToken(Claims);
        return UserLoginVO.builder()
                .id(user.getUserId())
                .openid(user.getOpenid())
                .token(jwt)
                .build();
    }

    @Override
    public User code(String code) {
        //调用微信接口获得当前用户的openid
        String openid=getOpenid(code);
        //判断openid是否为空，若为空则表示失败，返回登陆异常
        //if(openid==null){
        //    throw new AppException(AppExceptionCodeMsg.OPEN_ID_NULL);
        //}
        return loginMapper.getByOpenid(openid);
    }

    private String getOpenid(String code){
        //调用微信接口获得当前用户的openid
        Map<String,String> map=new HashMap<>();
        map.put("appid",weChatProperties.getAppId());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type","authorization_code");
        String json= HttpClientUtils.doGet(WX_LOGIN,map);

        JSONObject jsonObject= JSON.parseObject(json);
        String openid=jsonObject.getString("openid");
        return openid;
    }
}
