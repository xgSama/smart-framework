package com.xgsama.framework.helper;

import com.xgsama.framework.ConfigConstant;
import com.xgsama.framework.util.PropsUtil;

import java.util.Properties;

/**
 * ConfigHelper
 *
 * @author xgSama
 * @date 2020/11/20 21:49
 */
public class ConfigHelper {
    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

}
