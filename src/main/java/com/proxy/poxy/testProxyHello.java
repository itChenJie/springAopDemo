package com.proxy.poxy;

import com.proxy.hello.impl.Hello;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class testProxyHello implements InvocationHandler {
    private Object proxy;
    //目标
    private Object target;

    public Object biea(Object target,Object proxy){
        this.proxy = proxy;
        this.target = target;
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(),this.target.getClass().getInterfaces(),this);
    }

    /**
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object reulert = null;
        Class clas = this.proxy.getClass();
        Method start = clas.getDeclaredMethod("start",new Class[]{Method.class});
        start.invoke(this.proxy,new Object[]{method});
        method.invoke(this.target,args);
        Method end = clas.getDeclaredMethod("end",new Class[]{Method.class});
        end.invoke(this.proxy,new Object[]{method});
        return reulert;
    }
}
