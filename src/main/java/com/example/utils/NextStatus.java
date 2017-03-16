package com.example.utils;

/**
 * Created by liuxin on 17/2/8.
 */
public class NextStatus {
    private int code;
    private String msg;

    public NextStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public NextStatus(OrderStatus os) {
        this.code = os.getCode();
        this.msg=os.getMsg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
