package com.example.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @Package: com.example.channel
 * @Description: 测试异步监听
 * @author: liuxin
 * @date: 17/4/13 下午7:31
 */
public class NioChannel {
    public static void main(String[] args) throws Exception {
        DatagramChannel dc = DatagramChannel.open();
        dc.socket().bind(new InetSocketAddress(9999));
        ByteBuffer bb = ByteBuffer.allocate(48);
        bb.clear();
        dc.receive(bb);
    }
}
