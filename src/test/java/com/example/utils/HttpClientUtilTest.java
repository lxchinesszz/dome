package com.example.utils;


import com.example.builder.User;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.springframework.util.ClassUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.*;


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

    /**
     * 模拟银行去建立一个流对象
     * http://localhost:8080/chinapay
     *
     * @throws Exception
     */
    @Test//https://callback.boluomeet.com/shenghuojiaofei/chinapay/order_status
    public void test1() throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        byte []b="76612964e71d2e1b<?xml version=\"1.0\" encoding=\"UTF-8\"?><workflows xmlns=\"http://convenience.chinapay.com/schema/outerinterface\"><orderNotice><branchId>00005616</branchId><ordId>0031000000140930</ordId><ordDate>20170329</ordDate><ordStat>000</ordStat><billList><bill><merSysId>00000310</merSysId><billNo>000003102017032914340500369528</billNo><billAmt>6300</billAmt><billDate>20170329</billDate><merAccount>035520160810019500063003</merAccount><scBillStat>2</scBillStat><individualArea></individualArea></bill></billList><resv1></resv1><resv2></resv2><resv3></resv3><resv4></resv4></orderNotice></workflows>".getBytes();
        HttpPost httpPost = new HttpPost("http://localhost:6969/shenghuojiaofei/chinapay/order_status");
        InputStreamEntity entity = new InputStreamEntity(new ByteArrayInputStream(b), b.length);
        httpPost.setEntity(entity);
//        httpPost.setHeader("Content-Type","application/octet-stream");
        org.apache.http.HttpResponse response = httpClient.execute(httpPost);
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        System.out.println("输出返回数据" + br.readLine());
    }

    @Test
    public void tree() {
        Class[] c = ClassUtils.getAllInterfaces(new String());
        for (Class cc:c){
            System.out.println(cc.getName());
        }
        Arrays.asList(1, 2, 3, 4).stream().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2 ? 1 : -1;
            }
        }).skip(4 - 2).forEach(System.out::print);


//       Optional<Integer>opts=Optional.of(null);//不能传入null否则直接报错

        Optional<User> opt = Optional.ofNullable(null);//相对宽容，如果是null就返回该对象
        Optional<User> opts = Optional.ofNullable(new User("liuxin1", "24", "hello"));

        System.out.println(opts.orElse(null).getAge());
        //如果不存在就打打印整个对象
        System.out.println(opt.orElse(new User("liuxin2", "24", "hello")).getName());
        //如果存在就打印之前存在的信息
        System.out.println(opts.orElse(new User("liuxin", "24", "hello")).getName());

        opts.map(user -> {
            return user.getName();
        }).map(name -> {
            return name.toUpperCase();
        }).ifPresent(System.out::println);

        //获得如果没有就返回个函数，如果有就返回之前的
        //orElse()和orElseGet()
        String name = opts.orElseGet(() -> {
            return new User("1", "1", "1");
        }).getName();
        System.out.println(name);


    }

    @Test
    public void test2() {
        int free = 200;
        saveDefault:
        save(free);
    }

    public void saveDefault() {
        System.out.println("-------");
    }

    public void save(int a) {
        Thread.currentThread().getContextClassLoader().getResourceAsStream("");
    }
}