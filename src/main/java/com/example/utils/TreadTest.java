package com.example.utils;

import org.apache.http.client.fluent.Async;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.concurrent.FutureCallback;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @Package: com.example.utils
 * @Description: 多线程异步请求
 * @author: liuxin
 * @date: 17/3/16 上午9:42
 */
public class TreadTest {
    public static void main(String[] args)throws Exception{
        //定义一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Async async = Async.newInstance().use(executorService);

        Request[] requests = new Request[]{
                Request.Get("http://www.baidu.com/"),
                Request.Get("http://www.jianshu.com/")
        };
        Queue<Future<Content>> queue = new LinkedList<Future<Content>>();

        for (final Request request : requests) {
            Future<Content> future = async.execute(request, new FutureCallback<Content>() {
                public void failed(final Exception ex) {
                    System.out.println("出错了");
                }

                public void completed(final Content content) {
                    System.out.println("Request completed: " + content.asString());
                }

                public void cancelled() {
                    System.out.println("取消了");
                }
            });
            queue.add(future);
        }

        while (!queue.isEmpty()) {
            Future<Content> future = queue.remove();
            try {
                future.get();
            } catch (ExecutionException ex) {
            }
        }
        System.out.println("Done");
        executorService.shutdown();

    }



    public void conCurrent(){
        ExecutorService executorService=Executors.newFixedThreadPool(3);
    }
}
