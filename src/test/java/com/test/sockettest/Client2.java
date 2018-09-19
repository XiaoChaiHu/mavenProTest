package com.test.sockettest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client2 {
    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress=new InetSocketAddress("127.0.0.1",8765);
        //建立缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //打开通道
        try (SocketChannel socketChannel = SocketChannel.open()) {

            //进行连接
            socketChannel.connect(inetSocketAddress);
            while (true){
                //定义一个字节数组，然后使用系统录入功能：
                byte[] bytes = new byte[1024];
                System.in.read(bytes);

                //把数据放到缓冲区中
                buf.put(bytes);
                //对缓冲区进行复位
                buf.flip();
                //写出数据
                socketChannel.write(buf);
                //清空缓冲区数据
                buf.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
