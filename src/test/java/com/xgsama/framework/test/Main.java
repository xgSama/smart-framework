package com.xgsama.framework.test;

import com.xgsama.framework.HelpLoader;
import com.xgsama.framework.bean.Param;
import com.xgsama.framework.helper.BeanHelper;
import com.xgsama.framework.test.controller.HelloController;
import com.xgsama.framework.test.service.HelloService;

import java.util.Map;

/**
 * Main
 *
 * @author xgSama
 * @date 2021/3/11 15:21
 */
public class Main {
    public static void main(String[] args) {
        HelpLoader.init();
        for (Map.Entry<Class<?>, Object> entry : BeanHelper.getBeanMap().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

//        HelloController helloController = (HelloController) BeanHelper.getBeanMap().get(HelloController.class);
//        helloController.sayHello(new Param(null));

        HelloService service = (HelloService) BeanHelper.getBeanMap().get(HelloService.class);
        service.sayHello();
    }
}
