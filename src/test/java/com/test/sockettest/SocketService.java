package com.test.sockettest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class SocketService implements Runnable {

    private Selector selector;

    private ByteBuffer redbyteBuffer = ByteBuffer.allocate(1024);

    private ByteBuffer writeByteBuffer = ByteBuffer.allocate(1024);

    public SocketService(int port) {
        try {
            //打开多路复用器
            this.selector = Selector.open();
            ServerSocketChannel ssc = ServerSocketChannel.open();
            //设置服务器通道为非阻塞模式
            ssc.configureBlocking(false);
            //绑定地址
            ssc.bind(new InetSocketAddress(port));
            //把服务器通道注册到多路复用器上，并且监听阻塞事件
            ssc.register(this.selector, SelectionKey.OP_ACCEPT);

            System.out.println("Server start, port :" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                //1 必须要让多路复用器开始监听
                this.selector.select();
                //2 返回多路复用器已经选择的结果集
                Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
                //3 进行遍历
                while (keys.hasNext()) {
                    //4 获取一个选择的元素
                    SelectionKey key = keys.next();
                    //5 直接从容器中移除就可以了
                    keys.remove();
                    //6 如果是有效的
                    if (key.isValid()) {
                        //7 如果为阻塞状态
                        if (key.isAcceptable()) {
                            this.accept(key);
                        }
                        if (key.isReadable()) {
                            this.read(key);
                        }
                        if (key.isWritable()) {
                            this.write(key);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void write(SelectionKey key) {
    }

    private void read(SelectionKey key) {
        try {
            this.redbyteBuffer.clear();
            SocketChannel channel = (SocketChannel) key.channel();
            int n = channel.read(this.redbyteBuffer);
            if (n<0){
                key.channel().close();
                return;
            }
            //5 有数据则进行读取 读取之前需要进行复位方法(把position 和limit进行复位)
            this.redbyteBuffer.flip();
            //6 根据缓冲区的数据长度创建相应大小的byte数组，接收缓冲区的数据
            byte[] bytes = new byte[this.redbyteBuffer.remaining()];
            //7 接收缓冲区数据
            this.redbyteBuffer.get(bytes);
            //8 打印结果
            String body = new String(bytes).trim();
            System.out.println("Server : " + body);
        }catch (Exception e){

        }
    }

    private void accept(SelectionKey key) {
        try {
            //1 获取服务通道
            ServerSocketChannel channel = (ServerSocketChannel) key.channel();
            //2 执行阻塞方法，接收客户端发送过来的socket
            SocketChannel accept = channel.accept();
            //3 设置阻塞模式
            accept.configureBlocking(false);
            accept.register(this.selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new Thread(new SocketService(8765)).start();
    }
}
