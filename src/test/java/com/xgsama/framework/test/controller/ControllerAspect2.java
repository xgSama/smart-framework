package com.xgsama.framework.test.controller;

import com.xgsama.framework.annotation.Aspect;
import com.xgsama.framework.annotation.Controller;
import com.xgsama.framework.proxy.AspectProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.time.Instant;

/**
 * ControllerAspect
 *
 * @author xgSama
 * @date 2021/1/15 20:58
 */
@Aspect(Controller.class)
public class ControllerAspect2 extends AspectProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect2.class);

    private long begin;


    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        LOGGER.debug("================ BEGIN 2 ================");
        LOGGER.debug(String.format("method: %s", method.getName()));
        begin = Instant.now().toEpochMilli();

    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        LOGGER.debug(String.format("method: %s", method.getName()));
        LOGGER.debug("================  END 2  ================");
    }
}
