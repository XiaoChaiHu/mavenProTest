package com.test.lambda.methodReferences;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class test {

    public static void main(String[] args) {
        Map<String,Function<String,Apple>>map=new HashMap<>();

        Function<String,Apple> greenApple=(x)->new Apple(x);
        greenApple.apply("绿色");
        Function<String,Apple>red=Apple::new;

        ITestGirl<String,Integer,Integer,Girl>girl=Girl::new;

    }
}
