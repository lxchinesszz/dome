package com.example.utils;

import com.example.DemoApplicationTests;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;
import org.omg.PortableServer.POA;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.PipedOutputStream;

import static org.junit.Assert.*;

/**
 * @Package: com.example.utils
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/2/24 下午5:04
 */
public class HttpClientUtilTest {

    @Test
    public void doHttpRequest() throws Exception {
        GetMethod getMethod = new GetMethod("https://dev-api.otosaas.com/shenghuojiaofei/v1/categories?cityId=021&channel=fft");
        String ss = HttpClientUtil.doHttpRequest(getMethod, "UTF-8");
        System.out.println(ss);
    }

}