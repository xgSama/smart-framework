package com.xgsama.framework;

import com.xgsama.framework.helper.*;
import com.xgsama.framework.util.ClassUtil;

/**
 * HelpLoader
 *
 * @author xgSama
 * @date 2020/11/21 17:10
 */
public final class HelpLoader {
    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                // AopHelper要在IocHelper之前加载，因为首先要通过AopHelper获取代理对象
                // 然后才能通过IocHelper进行依赖注入
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };

        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}
