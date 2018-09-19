package com.test.netty.demo1;

import com.test.netty.time.TimeServerHandle;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiscardServer {

    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        //用来接收进来的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //用来处理已经被接收的连接
        // 一旦‘boss’接收到连接，就会把连接信息注册到‘worker’上
        //如何知道多少个线程已经被使用，如何映射到已经创建的Channels上都需要依赖于EventLoopGroup的实现，并且可以通过构造函数来配置他们的关系
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //ServerBootstrap 是一个启动NIO服务的辅助启动类
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    //这里我们指定使用NioServerSocketChannel类来举例说明一个新的Channel如何接收进来的连接
                    .channel(NioServerSocketChannel.class)
                    //这里的事件处理类经常会被用来处理一个最近的已经接收的Channel
                    //ChannelInitializer是一个特殊的处理类，他的目的是帮助使用者配置一个新的Channel
                    //也许你想通过增加一些处理类比如DiscardServerHandle来配置一个新的Channel或者其对应的ChannelPipeline来实现你的网络程序
                    //当你的程序变的复杂时，可能你会增加更多的处理类到pipline上，然后提取这些匿名类到最顶层的类上。
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TimeServerHandle());
                        }
                    })
                    //你可以设置这里指定的通道实现的配置参数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //option()是提供给NioServerSocketChannel用来接收进来的连接
                    //childOption()是提供给由父管道ServerChannel接收到的连接，在这个例子中也是NioServerSocketChannel。
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future=b.bind(this.port).sync();
            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        int port=8089;
        new DiscardServer(port).run();
    }
}
