package com.xgsama.framework.util;

import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Set;

/**
 * ClassUtilTest
 *
 * @author xgSama
 * @date 2020/11/21 11:17
 */
public class ClassUtilTest {
    @Test
    public void test() {
        Set<Class<?>> classSet = ClassUtil.getClassSet("com.xgsama.framework");

        if (classSet.isEmpty()) {
            System.out.println("empty");
        } else {
            for (Class<?> aClass : classSet) {
                System.out.println(aClass.getName());
            }
        }
    }

    @Test
    public void test1() {
        File file = new File("/F:/xgsama/smart-framework/target/classes/com/xgsama/framework/helper");
        System.out.println(file.getAbsolutePath());
        System.out.println(StandardCharsets.UTF_8.name());
    }
}
