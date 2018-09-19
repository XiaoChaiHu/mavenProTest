package com.test.jvm.demo1;

public class Test03 {

    /**
     * 测试垃圾回收标记
     * @param args
     */
    public static void main(String[] args) {

        //-Xms1024m -Xmx1024m -XX:+useSerialGC -XX:MaxTenuringThreshold=15 -XX:+PrintGCDetails
        for (int k=0;k<20;k++){
            for (int j=0;j<300;j++){
                byte[] bytes=new byte[1024*1024];
            }
        }

    }
}
