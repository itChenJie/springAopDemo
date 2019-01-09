package com.proxy.logger.impl;

import com.proxy.logger.Ilogger;

import java.lang.reflect.Method;
import java.util.Date;

public class DLogger implements Ilogger {
    @Override
    public void start(Method method) {
        System.out.println(new Date()+method.getName()+"start hello");
    }

    @Override
    public void end(Method method) {
        System.out.println(new Date()+method.getName()+"end hello");
    }
}
