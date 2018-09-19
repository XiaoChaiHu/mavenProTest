package com.test.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class demo1 {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("TaoTao");
        names.add("ZHIFULAI");
        List<String> lowerName1 = names.stream().map((String name) -> {
            return name.toLowerCase();
        }).collect(Collectors.toList());

        //参数类型省略–绝大多数情况，编译器都可以从上下文环境中推断出lambda表达式的参数类型
        //当lambda表达式的参数个数只有一个，可以省略小括号。lambda表达式简写为：
        lowerName1 = names.stream().map(name -> {
            return name.toLowerCase();
        }).collect(Collectors.toList());

        //当lambda表达式只包含一条语句时，可以省略大括号、return和语句结尾的分号。lambda表达式简化为
        lowerName1 = names.stream().map(name -> name.toLowerCase()).collect(Collectors.toList());

        // 使用Method Reference
        lowerName1=names.stream().map(String::toLowerCase).collect(Collectors.toList());

        //lambda表达式的三个重要组成部分
        //1.输入参数
        //2.可执行语句
        //3.存放外部变量的空间
        //不过lambda表达式访问外部变量有一个非常重要的限制：变量不可变（只是引用不可变，而不是真正的不可变）

        List<String>filterList= names.parallelStream().filter(name->name.equals("TaoTao")).collect(Collectors.toList());
        filterList.forEach(item->{
            System.out.println(item);
        });
    }
}
