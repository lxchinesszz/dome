package com.example.Nashorn;

import javax.script.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Package: com.example.Nashorn
 * @Description: js解析引擎
 * @author: liuxin
 * @date: 17/3/23 下午5:11
 */
public class JavaSrcipt {
    public static void main(String[] args) throws Exception {
        String func = "function f(){return 1;};\n" +
                "print(f() + 1);";
        ScriptEngineManager manager = new ScriptEngineManager();//JS管理器
        ScriptEngine scriptEngine = manager.getEngineByName("JavaScript");
        System.out.println(scriptEngine.getClass().getName());
        System.out.println(scriptEngine.eval(func));


        Compilable compilable = (Compilable) scriptEngine;//强制转换为预编译
        Bindings bindings = scriptEngine.createBindings();//获得绑定对象


        CompiledScript JSFunction = compilable.compile("function Sum (a, b) {\n" +
                "    return a + b;\n" +
                "}\n" +
                "\n" +
                "Sum(a,b);");
        bindings.put("a", 1);
        bindings.put("b", 2);
        Object obj = JSFunction.eval(bindings);
        System.out.println(obj);

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("Math.js");
        System.out.println(is.toString());
        System.out.println(Thread.currentThread().getContextClassLoader().getParent());



        InputStream in = JavaSrcipt.class.getClassLoader().getResourceAsStream("Math.js");
        System.out.println(JavaSrcipt.class.getClassLoader().getParent());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuffer sb = new StringBuffer();
        String len;
        while ((len = reader.readLine()) != null) {
            sb.append(len);
        }
        System.out.println(sb.toString());
    }
}
