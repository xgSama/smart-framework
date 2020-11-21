package com.xgsama.framework.util;

/**
 * 转型操作工具类
 *
 * @author xgSama
 * @date 2020/11/21 17:19
 */
public final class CastUtil {


    /**
     * 转为 String 型（提供默认值）
     */
    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    /**
     * 转为 String 型
     */
    public static String castString(Object obj) {
        return CastUtil.castString(obj, "");
    }


    public static long castLong(Object obj) {
        return CastUtil.castLong(obj, 0);
    }

    /**
     * 转为 long 型（提供默认值）
     */
    public static long castLong(Object obj, long defaultValue) {
        long longValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    longValue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }


}
