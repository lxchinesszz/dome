package com.example.builder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Package: com.example.builder
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/3/8 下午5:15
 */
public class UserBuilder {
    private String name;
    private String des;
    private String age;

   public UserBuilder(String name, String age) {
        super();
        this.name = name;
        this.age = age;
    }

    public UserBuilder setDes(String des) {
        this.des = des;
        return this;
    }

    public UserBuilder builderUser(String name, String age) {
        this.age = age;
        this.name = name;
        return this;
    }

    public User build() {
        return new User(this.name, this.age,this.des);
    }

}