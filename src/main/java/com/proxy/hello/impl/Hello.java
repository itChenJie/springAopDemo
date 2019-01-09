package com.proxy.hello.impl;

import com.proxy.annotation.aopTest;
import com.proxy.hello.Ihello;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Hello implements Ihello {
    private String[] index;
    public Hello(){

    }
    @aopTest
    @Override
    public String sayHello(String str) {
        System.out.println("Hello"+ index[5]);
        return "1";
    }
    public String sayHello(String str,String name){
        return "123";
    }
    public String sayHello(Object obj,String str){
        return "obj";
    }
}
