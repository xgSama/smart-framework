package com.xgsama.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * ClassUtil
 *
 * @author xgSama
 * @date 2020/11/20 21:50
 */
public final class ClassUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     *
     * @return ClassLoader
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }


    /*
     * @param className     类名
     * @param isInitialized 是否初始化
     * @return Class
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {

        Class<?> cls;

        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class failure", e);
            throw new RuntimeException(e);
        }

        return cls;
    }

    public static Class<?> loadClass(String name) {
        return loadClass(name, true);
    }

    /**
     * 获取指定包下的所有类
     *
     * @param packageName 包名
     * @return 所有类的Set集合
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();

        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")) {
                        // replaceAll("%20", ""): 消除空格
                        String packagePath = url.getPath().replaceAll("%20", "");
                        addClass(classSet, packagePath, packageName);

                    } else if (protocol.equals("jar")) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while (jarEntries.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf("."))
                                                .replaceAll("/", ".");
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.error("get class set failure", e);
            throw new RuntimeException(e);
        }

        return classSet;
    }

    /**
     * 根据包的路径和包名加载class文件
     *
     * @param classSet    类Set集合
     * @param packagePah  包文件路的径
     * @param packageName 包名
     */
    private static void addClass(Set<Class<?>> classSet, String packagePah, String packageName) {

        // 获取包路径下的class文件或者子包名
        File[] files = new File(packagePah).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });

        if (files == null) {
            return;
        }

        for (File file : files) {
            String fileName = file.getName();
            // 如果是class文件则直接加载
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (StringUtils.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            }
            // 是文件夹的话则递归进入此文件夹加载其中class文件
            else {
                // 拼接子包路径
                String subPackagePath = fileName;
                if (StringUtils.isNotEmpty(packageName)) {
                    subPackagePath = packagePah + "/" + subPackagePath;
                }
                // 拼接子包名
                String subPackageName = fileName;
                if (StringUtils.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }

                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    /**
     * 根据全限定类名加载class文件
     *
     * @param classSet  集合
     * @param className 全限定类名
     */
    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }
}
