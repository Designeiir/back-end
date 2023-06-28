package com.example.backend.exception;

import lombok.Data;


public enum OSExceptionEnum {

    //文件相关的为1开头


    // 前端参数错误的为2开头
    USER_PARAM_NULL(20001, "用户名为空"),

    TOKEN_NULL(20002, "token为空，请先登录"),

    TOKEN_VALID_ERROR(20003, "token验证失败"),

    PARAM_ERROR(20004, "参数错误"),

    // 业务逻辑有问题的为3开头
    USER_EMPTY(30001, "找不到用户"),

    PASSWORD_ERROR(30002, "密码错误"),

    USER_EXISTS(30003, "用户名已存在"),

    // 数据库相关错误的为4开头
    EMPTY_DATA(40001, "数据为空"),

    INVITE_CODE_ERROR(40002, "邀请码错误"),

    ;

    private final int errorCode;

    private final String errorMsg;

    OSExceptionEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }


}
