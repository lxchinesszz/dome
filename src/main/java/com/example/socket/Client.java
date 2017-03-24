package com.example.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @Package: com.example.socket
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/3/23 下午8:36
 */
public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 10086);
        socket.getOutputStream().write("你好我要连接你".getBytes());
        InputStream in = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader reader1 = new BufferedReader(reader);
        String info = null;
        while ((info = reader1.readLine()) != null) {
            System.out.println("服务端回复说：" + info);
        }

        reader1.close();
        reader.close();
        in.close();
        socket.close();
    }
}
