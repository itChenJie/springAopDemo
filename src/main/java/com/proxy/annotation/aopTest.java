package com.proxy.annotation;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface aopTest {
}
