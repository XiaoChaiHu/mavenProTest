package com.test.lambda.methodReferences;

import lombok.Data;

@Data
public class Apple {

    public Apple(String color){
        this.color=color;
    }


    private String color;

    private Integer weight;

}
