package com.xgsama.framework.bean;

import java.lang.reflect.Method;

/**
 * 封装Action信息
 *
 * @author xgSama
 * @date 2020/11/21 16:48
 */
public class Handler {

    private Class<?> controllerClass;

    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod= actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
