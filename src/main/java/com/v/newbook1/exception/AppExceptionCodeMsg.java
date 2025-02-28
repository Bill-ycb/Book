package com.v.newbook1.exception;


public enum AppExceptionCodeMsg {
    WRONG_USER("更改的不是自己发布的书籍"),
    OPEN_ID_NULL("openid is null,can't get it"),
    WRONG_ORDERWAY("排序方式不符合要求"),
    HAVEBEEN("该书已被您收藏");

    private String msg ;
    public String getMsg() {
        return msg;
    }
    AppExceptionCodeMsg(String msg){
        this.msg = msg;
    }
}
