package com.test.jvm.demo2;

import java.util.HashMap;
import java.util.Map;

public class MyThread implements Runnable {

    Map<Long,byte[]> map=new HashMap<>();

    @Override
    public void run() {
        try {
            while (true){
                if (map.size()*512/1024/1024>=450){//大于450M时清理内存
                    System.out.println("==============装备清理============："+map.size());
                    map.clear();
                }
                for (int i=0;i<1024;i++){
                    map.put(System.nanoTime(),new byte[512]);
                }
                Thread.sleep(1);
            }
        }catch (Exception e){

        }
    }
}
