package com.example.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @Package: com.example.domain
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/3/6 下午2:52
 */
@Document(collection = "jpa_user_test")
public class User implements Serializable {
    @Id
    Long id;

    String name;
    Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
