package com.example.socket;

import org.apache.commons.httpclient.methods.InputStreamRequestEntity;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Package: com.example.socket
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/3/23 下午8:28
 */
public class Server {
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(10086);
        Socket socket = serverSocket.accept();

        InputStream in = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader reader1 = new BufferedReader(reader);
        String info = null;
        while ((info = reader1.readLine()) != null) {
            System.out.println("客户端说:" + info);
        }
        socket.shutdownInput();

        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.write("我是服务端");
        pw.flush();
        pw.close();
        os.close();
        reader1.close();
        reader.close();
        in.close();
        socket.close();
        serverSocket.close();
    }
}
