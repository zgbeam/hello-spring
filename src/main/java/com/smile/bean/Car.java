package com.smile.bean;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Car {

    public void init() {
        System.out.println("init method");
    }

    public void destroy() {
        System.out.println("destroy method");
    }
}
