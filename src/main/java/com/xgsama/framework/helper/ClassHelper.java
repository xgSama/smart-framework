package com.xgsama.framework.helper;

import com.xgsama.framework.annotation.Controller;
import com.xgsama.framework.annotation.Service;
import com.xgsama.framework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * 类操作助手
 *
 * @author xgSama
 * @date 2020/11/21 12:28
 */
public final class ClassHelper {
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * 获取应用包名下的所有类
     *
     * @return CLASS_SET
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    /**
     * 获取所有Service类
     *
     * @return set
     */
    public static Set<Class<?>> getServiceClassSet() {

        return getAnnotationClassSet(Service.class);
    }

    /**
     * 获取所有Controller类
     *
     * @return set
     */
    public static Set<Class<?>> getControllerClassSet() {

        return getAnnotationClassSet(Controller.class);
    }

    /**
     * 获取带有某个注解的类
     *
     * @param anoType 注解class
     * @return set
     */
    private static Set<Class<?>> getAnnotationClassSet(Class<? extends Annotation> anoType) {
        Set<Class<?>> classSet = new HashSet<>();

        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(anoType)) {
                classSet.add(cls);
            }
        }

        return classSet;
    }

    /**
     * 获取应用包名下带有某注解的所有类
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass) {
        return getAnnotationClassSet(annotationClass);
    }

    /**
     * 获取应用包名下某父类（或接口）的所有子类（实现类）
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
        Set<Class<?>> classSet = new HashSet<>();

        for (Class<?> cls : CLASS_SET) {
            if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {
                classSet.add(cls);
            }
        }

        return classSet;
    }

    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanClassSet = new HashSet<>();

        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());

        return beanClassSet;
    }
}
