package com.example.web;

import com.example.config.MyLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.example.web
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/2/23 下午4:14
 */
@RestController
public class JsonRest {
    @MyLog(value = "拦截日志")
    @RequestMapping("/log")
    public String getLog(String ss,int s){
        return "<h1>Hello World</h1>"+ss;
    }
}
