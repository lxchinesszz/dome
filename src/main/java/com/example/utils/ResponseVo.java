package com.example.utils;


/**
 * Created by liuxin on 16/12/19.
 * 处理所有的响应包装类
 */
public class ResponseVo {

    public int code;
    public String message;
    public Object data;

    public ResponseVo() {
    }

    //默认是处理成功
    public ResponseVo(Object data) {
        ResponseStatus responseStatus = ResponseStatus.SUCCESS;
        this.code = responseStatus.getCode();
        this.message = responseStatus.getMessage();
        this.data = data;
    }

    public ResponseVo(OrderStatus orderStatus) {
        this.code = orderStatus.getCode();
        this.message = orderStatus.getMsg();
        this.data = null;
    }

    public ResponseVo(ResponseStatus responseStatus) {
        this.code = responseStatus.getCode();
        this.message = responseStatus.getMessage();
        this.data = null;
    }

    public ResponseVo(int code, String msg, Object object) {
        this.code = code;
        this.message = msg;
        this.data = object;
    }

    //允许自定义异常信息
    public ResponseVo(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }

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
