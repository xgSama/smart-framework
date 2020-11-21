package com.xgsama.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * PropsUtil
 *
 * @author xgSama
 * @date 2020/11/20 21:49
 */
public class PropsUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载属性文件
     *
     * @param propsPath 属性文件路径
     * @return Properties
     */
    public static Properties loadProps(String propsPath) {
        Properties properties = new Properties();
        InputStream is = null;

        try {
            if (StringUtils.isEmpty(propsPath)) {
                throw new IllegalArgumentException();
            }
            String suffix = ".properties";
            if (propsPath.lastIndexOf(suffix) == -1) {
                propsPath += suffix;
            }

            is = ClassUtil.getClassLoader().getResourceAsStream(propsPath);

            if (is != null) {
                properties.load(is);
            }
        } catch (Exception e) {
            LOGGER.error("加载属性文件出错！", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                LOGGER.error("释放资源出错！", e);
            }
        }

        return properties;
    }

    /**
     * 加载属性文件，并转为 Map
     */
    public static Map<String, String> loadPropsToMap(String propsPath) {
        Map<String, String> map = new HashMap<String, String>();

        Properties props = loadProps(propsPath);
        for (String key : props.stringPropertyNames()) {
            map.put(key, props.getProperty(key));
        }

        return map;
    }

}
