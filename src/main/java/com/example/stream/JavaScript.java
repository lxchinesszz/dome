package com.example.stream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Base64;

/**
 * @Package: com.example.stream
 * @Description: Jdk8中的JavaSrcipt引擎
 * @author: liuxin
 * @date: 17/3/20 下午7:02
 */
public class JavaScript {
    public static void main(String[] args) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        System.out.println(engine.getClass().getName());
        System.out.println("Result:" + engine.eval("function f() { " +
                "return 1; }; " +
                "f() + 1;")

        );
        String encode = Base64.getEncoder().encodeToString("test".getBytes());
        System.out.println(encode);

        String decode =new String(Base64.getDecoder().decode(encode));
        System.out.println(decode);
    }
}
