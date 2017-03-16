package com.example.utils;

import org.apache.http.client.fluent.Async;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Package: com.example.utils
 * @Description: 支持更大的并发：如果不采用连接池，每次连接都会打开一个端口，在大并发的情况下系统的端口资源很快就会被用完，导致无法建立新的连接
 * @author: liuxin
 * @date: 17/2/24 下午4:33
 */
@Component
public class HttpConnectionManager {
    private static PoolingHttpClientConnectionManager cm = null;
    private static CloseableHttpClient httpClient = null;

   static  {
        cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
    }

    public CloseableHttpClient getHttpClient(){
        return httpClient;
    }

    public static String get(String url) throws Exception {
        HttpPost post = new HttpPost(url);
        post.setHeader("User-Agent", "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.baidu.com)");
        BufferedReader in;
        CloseableHttpResponse response = httpClient.execute(post);
        in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String result=null;
        StringBuffer sb=new StringBuffer();
        while (in.readLine()!=null){
            sb.append(in.readLine());
        }
        in.close();
        response.close();
        result=sb.toString();
        return result;
    }

    public static void main(String[] args) throws Exception{

        ExecutorService executorService= Executors.newFixedThreadPool(3);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i < 5; i++) {
                    try {
                        Thread.sleep(20);// 为了测试出效果，让每次任务执行都需要一定时间
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("第次任务的第" + i + "次执行");
                }
            }
        });
        executorService.shutdown();
    }
}

