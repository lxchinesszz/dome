package com.example.socket;

import java.net.URL;

/**
 * @Package: com.example.socket
 * @Description: Java Socket编程
 * @author: liuxin
 * @date: 17/3/23 下午6:08
 */
public class SocketTest {
    public static void main(String[] args) throws Exception {

        URL baidu = new URL("http://www.baidu.com");
        URL url = new URL(baidu, "/index.html?username=tom#test");//？表示参数，#表示锚点
        System.out.println("协议"+url.getProtocol()); //获取协议
        System.out.println("主机"+url.getHost());//获取主机
        System.out.println("端口号"+url.getPort());//如果没有指定端口号，根据协议不同使用默认端口。此时getPort()方法的返回值为 -1
        System.out.println("文件路径"+url.getPath());//获取文件路径
        System.out.println("文件路径+参数"+url.getFile());//文件名，包括文件路径+参数
        System.out.println("相对路径"+url.getRef());//相对路径，就是锚点，即#号后面的内容
        System.out.println("参数"+url.getQuery());//查询字符串，即参数
    }
}
