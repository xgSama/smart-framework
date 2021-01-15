package com.xgsama.framework.test;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * ProxyDemo
 *
 * @author xgSama
 * @date 2021/1/15 19:57
 */
public class ProxyDemo {
    public static void main(String[] args) {
        Hello hello = new HelloProxy();
        hello.say("Jack");
    }

    @Test
    public void testDynamic() {
        Hello hello = new HelloImpl();

        DynamicProxy proxy = new DynamicProxy(hello);

        Hello helloProxy = (Hello) Proxy.newProxyInstance(
                hello.getClass().getClassLoader(),
                hello.getClass().getInterfaces(),
                proxy
        );

        helloProxy.say("jack");
    }


    @Test
    public void testDynamic2() {
        DynamicProxy dynamicProxy = new DynamicProxy(new HelloImpl());
        Hello proxy = dynamicProxy.getProxy();
        proxy.say("Jack");
    }
}
