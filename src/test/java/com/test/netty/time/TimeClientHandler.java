package com.test.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class TimeClientHandler extends ChannelHandlerAdapter {

    private ByteBuf buf;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        buf=ctx.alloc().buffer(8);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        buf.release();
        buf=null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /*ByteBuf m=(ByteBuf) msg;
        buf.writeBytes(m);
        m.release();
        if (buf.readableBytes()>=8){
            long currentTimeMillis = (buf.readInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        }*/
        ByteBuf m = (ByteBuf) msg;
        try {
            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        } finally {
            m.release();
        }



    }
}
