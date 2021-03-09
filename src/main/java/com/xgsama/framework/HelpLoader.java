package com.xgsama.framework;

import com.xgsama.framework.helper.*;
import com.xgsama.framework.util.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * HelpLoader
 *
 * @author xgSama
 * @date 2020/11/21 17:10
 */
public final class HelpLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelpLoader.class);

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
            LOGGER.info(cls.getName() + "初始化开始。。。。。。。。。");
            ClassUtil.loadClass(cls.getName());
        }
    }
}
