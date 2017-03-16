package com.example.utils;

import org.apache.http.client.fluent.Request;

import java.io.IOException;

/**
 * @Package: com.example.utils
 * @Description: 默认使用连接池连接
 * @author: liuxin
 * @date: 17/3/16 上午9:31
 */
public class FluentClient {

    public static String get(String url){
        String result=null;
       try {
           result=  Request.Get(url).connectTimeout(1000)
                   .socketTimeout(1000)
                   .execute().returnContent().asString();
       }catch (IOException ie){
       }
       return result;
    }

    public static void main(String[] args) {
        System.out.println(get("https://www.baidu.com/"));
    }

}
