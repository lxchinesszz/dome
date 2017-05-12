package com.example.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;


/**
 * @Package: com.example.netty
 * @Description: Netty
 * @author: liuxin
 * @date: 17/4/11 下午1:55
 */
public class NettyServer2 {
    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        // ExecutorService提交方法被调度,类似于线程提交执行，使用NIO解决,创建NioEventLoopGroup对象来处理事件，如接受新连接、接收数据、写数据等等
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        //监听新进来的TCP连接的通道
        bootstrap.group(eventExecutors).channel(NioServerSocketChannel.class).localAddress(8000).childHandler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                ChannelPipeline cp = channel.pipeline();
                //绑定处理器
//                cp.addLast(new EchoServerHandler());
//                cp.addLast(new EchoServerResponse());
                cp.addLast("decoder", new HttpRequestDecoder());//http服务器端对request解码
                cp.addLast("encoder", new HttpResponseEncoder());//http服务器端对response编码
                cp.addLast("aggregator", new HttpObjectAggregator(1048576));
                cp.addLast("deflater", new HttpContentCompressor());//压缩
                cp.addLast("handler", new HttpPrepareServerHandler());//自定义的处理器
            }
        });
        try {
            // 最后调用bootstrap.bind() 方法来绑定服务器,同步等待
            ChannelFuture channelFuture = bootstrap.bind().sync();
            //等待服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                //释放线程池资源
                eventExecutors.shutdownGracefully().sync();
            } catch (Exception e) {
                throw new RuntimeException("关闭异常", e);
            }
        }

    }

    private static class EchoServerHandler extends ChannelInboundHandlerAdapter {
        //用来接收数据
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("Server received: " + msg);
            ctx.write(msg);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }

    private static class EchoServerResponse extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            System.out.println("---输出---");
            ctx.channel().remoteAddress();
            super.write(ctx, msg, promise);
        }
    }
}
