package com.xgsama.framework.helper;

import com.xgsama.framework.annotation.Action;
import com.xgsama.framework.bean.Handler;
import com.xgsama.framework.bean.Request;
import com.xgsama.framework.util.ArrayUtil;
import com.xgsama.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 控制器助手类
 *
 * @author xgSama
 * @date 2020/11/21 16:50
 */
public final class ControllerHelper {

    /**
     * 1、封装一个ACTION_MAP，通过它来存放Request与Handler之间的映射关系
     * 2、通过ClassHelper来获取所有带有Controller注解的类
     * 3、遍历这些类，从Action注解中提取URL
     * 4、初始化Request与Handler之间的映射关系
     */

    public static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        // 获取所有的Controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();

        // 判断非空
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            // 遍历Controller类
            for (Class<?> controllerClass : controllerClassSet) {
                // 获取Controller类中的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)) {
                    // 遍历这些方法
                    for (Method method : methods) {
                        // 判断方法是否带有Action注解
                        if (method.isAnnotationPresent(Action.class)) {
                            // 从Action注解中获取映射规则
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            // 验证URL映射规则
                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                    // 获取请求方法与路径
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);

                                    // 初始化ActionMap
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * 获取Handler
     *
     * @param requestMethod 请求方法
     * @param requestPath   请求路径
     * @return Handler
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
