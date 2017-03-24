package com.example.utils;

import com.sun.deploy.util.StringUtils;
import freemarker.template.utility.StringUtil;
import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.lang.*;

/**
 * @Package: com.example.utils
 * @Description: 类加载器
 * @author: liuxin
 * @date: 17/3/21 上午11:32
 */
public class ClassUtil {
    /**
     * 获取指定包名下的所有类
     */
    public static List<Class<?>> getClassList(String packageName) {
        List<Class<?>> classList = new ArrayList<>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")) {
                        String packagePath = url.getPath();
                        addClass(classList, packagePath, packageName);
                    } else if (protocol.equals("jar")) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        JarFile jarFile = jarURLConnection.getJarFile();
                        Enumeration<JarEntry> jarEntries = jarFile.entries();
                        while (jarEntries.hasMoreElements()) {
                            JarEntry jarEntry = jarEntries.nextElement();
                            String jarEntryName = jarEntry.getName();
                            if (jarEntryName.endsWith(".class")) {
                                String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                if (className.substring(0, className.lastIndexOf(".")).equals(packageName)) {
//                                    classList.add(loadClass(className, false));
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
//            logger.error("获取类出错！", e);
            throw new RuntimeException(e);
        }
        return classList;
    }

    public static ClassLoader getClassLoader(){
        return  ClassLoader.getSystemClassLoader();
//        ClassUtil.class.getClassLoader();
    }

    private static void addClass(List<Class<?>> classList, String packagePath, String packageName) {
        try {
            // 获取包名路径下的 class 文件或目录
            File[] files = new File(packagePath).listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
                }
            });
            // 遍历文件或目录
            for (File file : files) {
                String fileName = file.getName();
                // 判断是否为文件或目录
                if (file.isFile()) {
                    // 获取类名
                    String className = fileName.substring(0, fileName.lastIndexOf("."));
                    if (true) {
                        className = packageName + "." + className;
                    }
                    // 执行添加类操作
//                    doAddClass(classList, className);
                } else {
                    // 获取子包
                    String subPackagePath = fileName;
                    if (true) {
                        subPackagePath = packagePath + "/" + subPackagePath;
                    }
                    // 子包名
                    String subPackageName = fileName;
                    if (true) {
                        subPackageName = packageName + "." + subPackageName;
                    }
                    // 递归调用
                    addClass(classList, subPackagePath, subPackageName);
                }
            }
        } catch (Exception e) {
//            logger.error("添加类出错！", e);
        }
    }

    public static void main(String[] args) {
        getClassList("com.example.utils.JsonUtil.class");
    }

}
