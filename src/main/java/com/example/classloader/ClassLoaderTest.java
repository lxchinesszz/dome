package com.example.classloader;


import com.example.config.MyLog;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Package: com.example.classloader
 * @Description: 测试一下类加载器
 * @author: liuxin
 * @date: 17/3/29 下午5:49
 */
public class ClassLoaderTest {
    /**
     * 思路：
     * 获得指定目录下面的类文件，->得到类名 UserBuilder 、User
     * 然后： com/example/builder/ 转换成com.example.builder 拼接类名
     * 将拼接好的类路径，交给Class.forName()解析
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Set<Class<?>> classSet = new HashSet<Class<?>>();
        String packageName = "com/example/builder";
        Enumeration<URL> urlEnumerations = Thread.currentThread().getContextClassLoader().getResources(packageName);
        while (urlEnumerations.hasMoreElements()) {
            URL url = urlEnumerations.nextElement();
            System.out.println(url.getProtocol() + ":" + url.getPath());///Users/liuxin/git//dome/target/classes/com/example/builder
            String protocol = url.getProtocol();
            if (protocol.equals("file")) {
                String x = "%e6%a8%a1%e4%bb%bf%e9%a1%b9%e7%9b%ae";
                File[] files = new File(url.getPath().replace(x, "模仿项目")).listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        System.out.println(pathname);
                        return true;
                    }
                });
                for (File file : files) {
                    if (file.getPath().endsWith(".class")) {
                        String className = file.getName().substring(0, file.getName().lastIndexOf("."));
                        System.out.println();
                        String classPath = packageName.replace("/", ".") + "." + className;
                        Class c = Class.forName(classPath, false, Thread.currentThread().getContextClassLoader());
                        classSet.add(c);
                    }
                }
            }
        }
        //获得注解，然后实例，放到bean池
        Iterator<Class<?>> it = classSet.iterator();
        while (it.hasNext()) {
            Class<?> cls = it.next();
            Method[] methods = cls.getMethods();
            for (Method m : methods) {
                if (m.isAnnotationPresent(MyLog.class)) {
                    System.out.println(m.getName());
                }
            }
        }
    }
}
