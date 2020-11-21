package com.xgsama.framework.bean;

/**
 * 返回数据对象
 *
 * @author xgSama
 * @date 2020/11/21 17:34
 */
public class Data {

    // 模型数据
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
