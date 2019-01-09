package com.example.proxy;

import com.proxy.hello.Ihello;
import com.proxy.hello.impl.Hello;
import com.proxy.logger.impl.DLogger;
import com.proxy.poxy.DynaProxyHello;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private Hello hello;

    @Test
    public void contextLoads() {
        //打印出Proxy具体实现类
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        //Ihello hello = (Ihello) new DynaProxyHello().bine(new Hello(),new DLogger());
        //  Ihello hello = (Ihello) new testProxyHello().biea(new Hello(),new DLogger());
        //hello.sayHello("华");
        /*String name = "glm";
        hello.sayHello(name);*/
        hello.sayHello("ww");
    }

}

