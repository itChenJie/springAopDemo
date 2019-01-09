package com.proxy;
import com.proxy.annotation.aopTest;
import com.proxy.hello.Ihello;
import com.proxy.hello.impl.Hello;
import com.proxy.logger.impl.DLogger;
import com.proxy.poxy.DynaProxyHello;
import org.springframework.stereotype.Component;

public class Test {


    public static void main(String[] args){
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        Ihello hello = (Ihello) new DynaProxyHello().bine(new Hello(),new DLogger());
//        hello.sayHello("华");
    }
}
