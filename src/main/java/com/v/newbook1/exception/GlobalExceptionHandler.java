package com.v.newbook1.exception;

import com.v.newbook1.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //@ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public <T> Result<T> exceptionHandler(Exception e){
        log.error(e.toString());
        //判断拦截到的Exception是不是我们自定义的异常类型
        if(e instanceof AppException){
            AppException appException = (AppException)e;
            return new Result<T>().error(appException.getMsg());
        }

        //拦截的异常不是我们自定义的异常(例如：数据库主键冲突)
        return new Result<T>().error("服务端异常："+e.toString());
    }
}
