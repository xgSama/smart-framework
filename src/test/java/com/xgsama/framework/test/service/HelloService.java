package com.xgsama.framework.test.service;

import com.xgsama.framework.annotation.Inject;
import com.xgsama.framework.annotation.Service;
import com.xgsama.framework.test.controller.HelloController;

/**
 * HelloService
 *
 * @author xgSama
 * @date 2021/3/11 16:43
 */
@Service
public class HelloService {

    @Inject
    HelloController helloController;

    public void sayHello() {
        helloController.sayHello(null);
    }
}
