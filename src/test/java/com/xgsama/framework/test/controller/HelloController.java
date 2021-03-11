package com.xgsama.framework.test.controller;

import com.xgsama.framework.annotation.Action;
import com.xgsama.framework.annotation.Controller;
import com.xgsama.framework.bean.Data;
import com.xgsama.framework.bean.Param;

/**
 * HelloController
 *
 * @author xgSama
 * @date 2021/3/11 15:19
 */
@Controller
public class HelloController {

    public HelloController() {
        System.out.println("**************************************************");
        System.out.println("HelloController 初始化。。。。。。。。。。。。。。。");
        System.out.println("**************************************************");
    }

    @Action("get:/hello")
    public Data sayHello(Param param) {

        System.out.println("开始执行。。。。。。。。。。。。。");
        return new Data("hello");
    }
}
