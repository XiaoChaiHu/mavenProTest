package com.test.lambda;

import java.util.stream.Stream;

public class Demo574 {

    public static void main(String[] args) {
        //Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
        //斐波那契数组
        Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]}).limit(20).forEach(n -> System.out.println("(" + n[0] + "," + n[1] + ")"));
    }
}
