package com.xgsama.framework;

import com.xgsama.framework.helper.BeanHelper;
import com.xgsama.framework.helper.ClassHelper;
import com.xgsama.framework.helper.ControllerHelper;
import com.xgsama.framework.helper.IocHelper;
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
                IocHelper.class,
                ControllerHelper.class
        };

        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}
