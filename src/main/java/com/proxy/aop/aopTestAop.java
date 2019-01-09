package com.proxy.aop;

import com.proxy.hello.impl.Hello;
import com.proxy.logger.impl.DLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
//@Order(-1) //优先级里面值越小级别越高
public class aopTestAop {
    private DLogger logger;
    @Pointcut(value = "@annotation(com.proxy.annotation.aopTest)")
    private void cutAopTest(){

    }

    /**
     *  前置通知：目标方法执行之前执行以下方法体的内容
     */
 /*   @After("cutAopTest()")
    public void after(){
        System.out.println("我要离开切入了！");
    }
    /**
    * 返回通知：目标方法正常执行完毕时执行以下代码
    */
    /*@Before("cutAopTest()")
    public void before(){
        System.out.println("我已经切入进来了！");
    }*/
    /**
     * 异常通知：目标方法发生异常的时候执行以下代码
     */
    @AfterThrowing(pointcut="cutAopTest()", throwing="e")
    public void afterThorwingMethod(JoinPoint point, NullPointerException e) throws ClassNotFoundException {
        //String methodName = point.getTarget().getClass().getName();//获取异常方法
        Map<String, Object> map = new HashMap<String, Object>();//存放出现异常的类和原因
        String targetName = point.getTarget().getClass().getName();//目标类
        String methodName = point.getSignature().getName();//目标方法名
        Object[] arguments = point.getArgs();//所有参数值
        Class targetClass = Class.forName(targetName);//工具目标类的名字获取到改目标类
        Method[] methods = targetClass.getMethods();//根据目标类获取他的所有方法
        for (Method method : methods) {//遍历目标类里的所有方法
            if (method.getName().equals(methodName)) {//判断方法名称和现在调用的方法名称是否一致
                Class[] clazzs = method.getParameterTypes();//目标类的参数类型
                //当目标类存在方法重载的情况下会存在多个类目一致并且参数长度一致但是可能类型不一致的情况
                //所以此处使用 用目标类的参数类型去和执行方法的参数长度进行对比的时候是不可靠的
                //只有再进行比较他们的所有类型是否一致才是最安全的方法
                if (clazzs.length == arguments.length) {//
                    map = this.getArgsAndMethodsName(method,clazzs,arguments);//执行获取目标方法的 参数 方法明 参数值方法
                    map.put("targetName",targetName);
                    map.put("methodName",methodName);
                    break;
                }
            }
        }
        System.out.println(map.toString());
        System.out.println("【异常通知】the method 【" +  methodName + "】 occurs exception: " + e);
    }
   @Around("cutAopTest()")
    public Object doAopTest(ProceedingJoinPoint point) throws Throwable {
        logger = new DLogger();
        MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        Class clas = this.logger.getClass();
        Method start = clas.getDeclaredMethod("start",new Class[]{Method.class});
        start.invoke(this.logger,new Object[]{method});

       Map<String, Object> map = new HashMap<String, Object>();//存放出现异常的类和原因
       String targetName = point.getTarget().getClass().getName();//目标类
       String methodName = point.getSignature().getName();//目标方法名
       Object[] arguments = point.getArgs();//所有参数值
       Class targetClass = Class.forName(targetName);//工具目标类的名字获取到改目标类
       Method[] methods = targetClass.getMethods();//根据目标类获取他的所有方法
       for (Method methodTest : methods) {//遍历目标类里的所有方法
           if (methodTest.getName().equals(methodName)) {//判断方法名称和现在调用的方法名称是否一致
               Class[] clazzs = method.getParameterTypes();//目标类的参数类型
               //当目标类存在方法重载的情况下会存在多个类目一致并且参数长度一致但是可能类型不一致的情况
               //所以此处使用 用目标类的参数类型去和执行方法的参数长度进行对比的时候是不可靠的
               //只有再进行比较他们的所有类型是否一致才是最安全的方法
               if (clazzs.length == arguments.length) {//
                   map = this.getArgsAndMethodsName(methodTest,clazzs,arguments);//执行获取目标方法的 参数 方法明 参数值方法
                   map.put("targetName",targetName);
                   map.put("methodName",methodName);
                   break;
               }

           }
       }
       /*System.out.println(map.toString());*/
/*        Method end = clas.getDeclaredMethod("end",new Class[]{Method.class});
        end.invoke(this.logger,new Object[]{method});*/
        return  point.proceed();
    }

    /**
     * 获取目标方法的 args MethodsName argsValue
     * @param method 方法
     * @param arguments  所有参数值
     * @return
     */
    public Map<String,Object> getArgsAndMethodsName(Method method,Class[] clazzs,Object[] arguments){
        Map<String,Object> map = new HashMap<String, Object>();
        Parameter[] argsName = method.getParameters();//获取参数名称
        //只有再进行比较他们的所有类型是否一致才是最安全的方法
            int i=0;
            for(Object obj : arguments){
                if(obj.getClass().equals(clazzs[i])){//判断参数的类型是否一致
                    map.put("argsName"+i,argsName[i].getName());//参数名
                    map.put("argsType"+i,clazzs[i]);//参数类型
                    map.put("argsValue"+i,obj);//参数值
                }
                i++;
            }
        return map;
    }
}
