package com.v.newbook1.exception;

public class AppException extends RuntimeException{

    private String msg = "服务器异常";


    public AppException(AppExceptionCodeMsg appExceptionCodeMsg){
        super();
        this.msg = appExceptionCodeMsg.getMsg();

    }

    public AppException(String msg){
        super();
        this.msg = msg;

    }


    public String getMsg() {
        return msg;
    }

}
