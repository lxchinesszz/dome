package com.example.builder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

/**
 * @Package: com.example.builder
 * @Description: 构建模式
 * @author: liuxin
 * @date: 17/3/8 下午4:35
 */
public class User implements Serializable {


    public  String name;
    public  String des;
    public  String age;

    public User(String name, String age,String des) {
        this.name = name;
        this.age = age;
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }

    public String getAge() {
        return age;
    }
    @JsonProperty("userName")
    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("Password")
    public void setDes(String des) {
        this.des = des;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
