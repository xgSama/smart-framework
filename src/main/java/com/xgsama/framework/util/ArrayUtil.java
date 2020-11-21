package com.xgsama.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * ArrayUtil
 *
 * @author xgSama
 * @date 2020/11/21 16:34
 */
public final class ArrayUtil {

    public static boolean isNotEmpty(Object[] array) {
        return !ArrayUtils.isEmpty(array);
    }

    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }


}
