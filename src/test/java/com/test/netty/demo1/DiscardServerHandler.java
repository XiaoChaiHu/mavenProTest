package com.test.netty.demo1;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

@ChannelHandler.Sharable
public class DiscardServerHandler extends ChannelHandlerAdapter {

    /**
     * 每当从客户端收到新的数据时，这个方法会在收到消息时被调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.write(msg);
        ctx.flush();
    }

    /**
     * 当出现Throwable对象才会被调用
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
