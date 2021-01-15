package com.xgsama.framework.test;

/**
 * HelloImpl
 *
 * @author xgSama
 * @date 2021/1/15 19:52
 */
public class HelloImpl implements Hello {
    @Override
    public void say(String name) {
        System.out.println("Hello! " + name);
    }
}
