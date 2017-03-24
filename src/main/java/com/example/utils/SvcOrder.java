package com.example.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Package: foxlife.dto
 * @Description: svc 服务返回的订单信息
 * @author: liuxin
 * @date: 17/2/21 下午5:21
 */
@JsonIgnoreProperties(value = {"channel","customerUserId","customerId"})
public class SvcOrder {
    //订单号
    private String id;
    //服务名
    private String name;
    //shenghuojiaofei
    private String orderType;
    //金额
    private double price;
    //用户id
    private String userId;
    //订单状态
    private int status;
    //订单状态描述
    private String displayStatus;
    //供应商名 fft
    private String channel;
    //企业id
    private Integer customerId;
    //第三方id
    private String customerUserId;
    //时间
    private String date;
    //下单时间戳
    private String ts;
    //失效时间
    private String expiredTime;
    //图标地址
    private String icon;
    //地址
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(String displayStatus) {
        this.displayStatus = displayStatus;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerUserId() {
        return customerUserId;
    }

    public void setCustomerUserId(String customerUserId) {
        this.customerUserId = customerUserId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
