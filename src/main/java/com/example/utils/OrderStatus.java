package com.example.utils;

/**
 * Created by liuxin on 16/12/26.
 */
public enum  OrderStatus {
    remove(-1,"已删除"),
    error(0,"异常"),
    orderde(1,"已下单"),
    be_paid(2,"待支付"),
    paid(3,"已支付"),
    complete(4,"已完成"),
    canceling(5,"取消中"),
    pay_back(6,"退款中"),
    pay_back_success(7,"已退款"),
    cancelled(8,"已取消"),
    dealing(9,"处理中"),
    failed(10,"订单失败"),
    waiting_refund(11,"等待退款"),
    jiaofeichenggong(9,"缴费成功");
    int code;
    String msg;

    OrderStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    OrderStatus(OrderStatus orderde) {
        this.code=orderde.code;
        this.msg=orderde.msg;
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
