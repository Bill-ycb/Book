package com.v.newbook1.intercepetor;

import com.alibaba.fastjson.JSONObject;
import com.v.newbook1.context.BaseContext;
import com.v.newbook1.result.Result;
import com.v.newbook1.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginCheckInterceptor implements HandlerInterceptor {
    //private final RedisTemplate<String,String> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle...");
        log.info("request:{}", request.toString());
        //1,获取请求的url
        String url = request.getRequestURL().toString();
        log.info("url:{}",url);
        //2,判断是否是login
        if(url.contains("login")){
            log.info("login登录放行");
            return true;
        }
        if(url.contains("code")){
            log.info("code登录放行");
            return true;
        }
        if(url.contains("t.html")){
            log.info("t.html");
            return true;
        }
        if(url.contains("api")){
            log.info("api放行");
            return true;
        }
        if(url.contains("swagger")){
            log.info("api放行");
            return true;
        }
        //3,获取请求头中的令牌,此处根据工具不同来解析token值，Apifox和swagger
        String jwt;
        //if(request.getHeader("User-Agent").contains("Apifox")){ }
        if(request.getHeader("Authorization")!=null) {jwt= request.getHeader("Authorization").substring(7);}
        else {jwt= request.getHeader("token");}
        log.info(jwt);
        //4,判断令牌是否存在
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，视为未登录");
            log.info(jwt);
            Result baseResponse =new Result();
            Result.error("NOT_LOGIN");

            response.getWriter().write(JSONObject.toJSONString(baseResponse));
            return false;
        }
        //5,解析token
        try {
            JwtUtils.decodeUser(jwt);
            //存放当前线程userId(唯一标识符)
            BaseContext.setCurrentId(Long.valueOf(JwtUtils.decodeUser(jwt).get("userId").toString()));
            log.info("CurrentUserId:{}",BaseContext.getCurrentId());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析失败，视为未登录");
            Result baseResponse =new Result();
            Result.error("NOT_LOGIN");

            response.getWriter().write(JSONObject.toJSONString(baseResponse));
            return false;
        }
        //6,放行
        log.info("已正常登录，放行");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
