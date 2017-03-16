package com.example.web;

import com.example.builder.User;
import com.example.builder.UserBuilder;
import com.example.config.ConfigValue;
import com.example.config.MyLog;
import com.example.utils.HttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;

/**
 * @Package: com.example.web
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/2/24 下午4:38
 */
@RestController
public class PoolConnectionRest {
    @Autowired
    HttpConnectionManager httpConnectionManager;
    @Autowired
    ConfigValue configValue;

    @MyLog("使用连接池访问")
    @RequestMapping("/conn")
    public void supConnection(HttpServletResponse res) {
        CloseableHttpClient closeableHttpClient = httpConnectionManager.getHttpClient();
        HttpGet httpGet = new HttpGet("www.baidu.com");
        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(httpGet);
            InputStream io = response.getEntity().getContent();
            byte[] b = new byte[io.available()];
            io.read(b);
            OutputStream ou = res.getOutputStream();
            ou.write(b);
            ou.flush();
            response.close();
        } catch (Exception e) {

        } finally {

        }

    }

    @RequestMapping("testError")
    public String test(String a, String b) throws Exception {
        if (a == null && b == null) {
            throw new Exception();
        }
        return "hello world";
    }
    @RequestMapping("user")
    public User getUser() {
        User user = new UserBuilder("SpringBoot", "23").setDes("Helloworld").build();
        return user;
    }
    @RequestMapping("myconfig")
    public String getConfigValue(){
        return configValue.getName();
    }
}
