package com.test.jvm.demo2;

public class StopTheWorld {

    public static void main(String[] args) {

        //参数
        //-Xmx512M -Xms512M -XX:+UseSerialGC -Xloggc:gc.log -XX:+PrintGCDetails -XX:+PrintGCDetails -Xmn1m -XX:PretenureSizeThreshold=50 -XX:MaxTenuringThreshold=1
        Thread thread=new Thread(new MyThread());

        thread.start();

    }
}
