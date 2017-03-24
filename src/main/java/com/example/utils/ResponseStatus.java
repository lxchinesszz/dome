package com.example.utils;

/**
 * Created by liuxin on 16/12/19.
 * 定义响应状态类
 */
public enum ResponseStatus {

    SUCCESS(0, "处理成功"),

    ERROR(1, "响应失败"),

    EXITS(2,"您输入的缴费号有误，请重新输入\n缴费号码可以查询纸质账单。"),

    IllEGAl(-1,"查询条件非法");

    ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    ResponseStatus(ResponseStatus responseStatus) {
        this.code = responseStatus.getCode();
        this.message = responseStatus.getMessage();
    }



    public int code;

    public String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
