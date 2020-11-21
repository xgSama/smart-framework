package com.xgsama.framework.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * CollectionUtil
 *
 * @author xgSama
 * @date 2020/11/21 16:28
 */
public final class CollectionUtil {

    /**
     * 判断集合是否非空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return CollectionUtils.isNotEmpty(collection);
    }

    /**
     * 判断集合是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }
}
