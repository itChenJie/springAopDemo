package com.proxy.poxy;

import javax.annotation.Resource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Resource
public class DynaProxyHello implements InvocationHandler {
    //调用对象 logger
    private Object proxy;
    //目标对象 hello
    private Object target;

    public Object bine(Object target,Object proxy){
        this.proxy = proxy;
        this.target = target;
        return  Proxy.newProxyInstance(this.target.getClass().getClassLoader(),this.target.getClass().getInterfaces(),this);
    }

    /**
     *
     * @param proxy 调用对象
     * @param method 目标对象
     * @param args 调用对象执行方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        //反射获取到调用方法的实例
        Class clas = this.proxy.getClass();
        //反射到调用类的start方法 name 类名
        Method start = clas.getDeclaredMethod("start",new Class[]{Method.class});
        //通过反射执行start方法
        start.invoke(this.proxy,new Object[]{method});
        //执行要处理的原本方法
        Object obj =method.invoke(this.target,args);
        //可以根据返回的结果去判断你要不要执行  end方法
        if("1".equals(obj)) {
            //反射到调用类的end方法
            Method end = clas.getDeclaredMethod("end",new Class[]{Method.class});
            //执行end
            end.invoke(this.proxy,new  Object[]{method});
        }
        return result;
    }
}
