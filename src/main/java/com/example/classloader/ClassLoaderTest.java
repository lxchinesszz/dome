package com.example.classloader;


import com.example.config.MyLog;
import org.apache.commons.codec.net.URLCodec;

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
        String packageName = "com/example";
        Enumeration<URL> urlEnumerations = Thread.currentThread().getContextClassLoader().getResources(packageName);
        while (urlEnumerations.hasMoreElements()) {
            URL url = urlEnumerations.nextElement();
            System.out.println(url.getProtocol() + ":" + url.getPath());//file:/Users/liuxin/git/%e6%a8%a1%e4%bb%bf%e9%a1%b9%e7%9b%ae/dome/target/classes/com/example/builder
            System.out.println(url.getProtocol() + ":" + new String(URLCodec.decodeUrl(url.getPath().getBytes())));
            String protocol = url.getProtocol();
            if (protocol.equals("file")) {
                String x = "%e6%a8%a1%e4%bb%bf%e9%a1%b9%e7%9b%ae";
                File[] files = new File(url.getPath().replace(x, "模仿项目")).listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        System.out.println(pathname);
                        return (pathname.isFile() && pathname.getName().endsWith(".class")) || pathname.isDirectory();
                    }
                });
                for (File file : files) {
                    if (file.getPath().endsWith(".class")) {
                        String className = file.getName().substring(0, file.getName().lastIndexOf("."));
                        System.out.println(className);
                        String classPath = packageName.replace("/", ".") + "." + className;
                        Class c = Class.forName(classPath, false, Thread.currentThread().getContextClassLoader());
                        classSet.add(c);
                    } else if (file.isDirectory()) {
                        Enumeration<URL> u = Thread.currentThread().getContextClassLoader().getResources(file.getPath());
                        URL url1 = u.nextElement();
                        String protocol1 = url1.getProtocol();
                        if (protocol.equals("file")) {
                            File[] files1 = new File(url.getPath().replace(x, "模仿项目")).listFiles(new FileFilter() {
                                @Override
                                public boolean accept(File pathname) {
                                    System.out.println(pathname);
                                    return (pathname.isFile() && pathname.getName().endsWith(".class")) || pathname.isDirectory();
                                }
                            });
                            for (File f : files1) {
                                if (f.getPath().endsWith(".class")) {
                                    String className = f.getName().substring(0, f.getName().lastIndexOf("."));
                                    System.out.println(className);
                                    String classPath = packageName.replace("/", ".") + "." + className;
                                    Class c = Class.forName(classPath, false, Thread.currentThread().getContextClassLoader());
                                    classSet.add(c);
                                }
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
    }
}
