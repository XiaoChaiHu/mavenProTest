package com.test.netty.time;

import com.test.netty.time.second.TimeDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {

    public static void main(String[] args) {
        int port=8089;
        EventLoopGroup workGroup=new NioEventLoopGroup();
        Bootstrap b=new Bootstrap();
        b.group(workGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new TimeDecoder(),new TimeClientHandler());
                    }
                });
        try {
            ChannelFuture f = b.connect("127.0.0.1", port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            workGroup.shutdownGracefully();
        }
    }
}
