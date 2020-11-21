package com.xgsama.framework.helper;

import com.xgsama.framework.annotation.Inject;
import com.xgsama.framework.util.ArrayUtil;
import com.xgsama.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类
 *
 * @author xgSama
 * @date 2020/11/21 16:23
 */
public final class IocHelper {
    static {
        // 获取所有的Bean类与Bean实例之间的映射关系
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (!beanMap.isEmpty()) {
            // 遍历Bean map
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                // 从bean map中取出Bean类与Bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();

                // 获取Bean类定义的所有成员变量（Bean Field）
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    // 遍历Bean Field
                    for (Field beanField : beanFields) {
                        // 判断当前Bean Field是否具有Inject注解
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);

                            if (beanFieldInstance != null) {
                                // 通过反射初始化BeanField的值
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
