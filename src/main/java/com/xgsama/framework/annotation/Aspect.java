package com.xgsama.framework.annotation;

import java.lang.annotation.*;

/**
 * Aspect
 *
 * @author xgSama
 * @date 2021/1/15 20:14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 注解
     */
    Class<? extends Annotation> value();
}
