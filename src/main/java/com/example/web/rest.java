package com.example.web;

import com.sun.deploy.net.HttpResponse;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

/**
 * Created by liuxin on 17/1/13.
 * 测试使用模板引擎
 */
@Controller
public class rest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/test")
    public ModelAndView get(ModelMap map) {
        map.put("user", "root");
        return new ModelAndView("test", map);
    }

    @RequestMapping("/logRank")
    public void test() {
        logger.info("info");
        logger.debug("debug");
        logger.error("error");
        logger.warn("warn");
    }

    @RequestMapping("/chinapay")
    public void test1() throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("https://callback.boluomeet.com/shenghuojiaofei/chinapay/order_status");
        InputStreamEntity entity = new InputStreamEntity(new ByteArrayInputStream("hello".getBytes()), "hello".getBytes().length);
        httpPost.setEntity(entity);
        org.apache.http.HttpResponse response = httpClient.execute(httpPost);
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        System.out.println("输出返回数据"+br.readLine());
    }

}
