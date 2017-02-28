package com.example.xmlmodel;

import com.sun.xml.internal.txw2.annotation.XmlNamespace;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @Package: com.example.xmlmodel
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/2/28 下午5:39
 */
@XmlRootElement(name = "MySchool")//根名
@XmlAccessorType(XmlAccessType.FIELD)//自动装配字段
public class School {
    @XmlAttribute//属性
    private String name;
    @XmlElementWrapper(name = "Grade")//外层包装
    private List<Classes>classes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Classes> getClasses() {
        return classes;
    }

    public void setClasses(List<Classes> classes) {
        this.classes = classes;
    }
}
