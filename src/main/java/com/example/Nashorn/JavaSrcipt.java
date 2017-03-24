package com.example.Nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @Package: com.example.Nashorn
 * @Description: js解析引擎
 * @author: liuxin
 * @date: 17/3/23 下午5:11
 */
public class JavaSrcipt {
    public static void main(String[] args)throws Exception {
        String func="function f(){return 1;};\n" +
                "print(f() + 1);";
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine scriptEngine = manager.getEngineByName("JavaScript");
        System.out.println(scriptEngine.getClass().getName());
        System.out.println(scriptEngine.eval(func));
    }
}
