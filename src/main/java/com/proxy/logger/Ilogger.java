package com.proxy.logger;

import java.lang.reflect.Method;

public interface Ilogger {
    void start(Method method );
    void end(Method method);
}
