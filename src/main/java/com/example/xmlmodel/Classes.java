package com.example.xmlmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @Package: com.example.xmlmodel
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/2/28 下午5:45
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Classes {
    @XmlAttribute
    private String className;
    @XmlElement(name = "studentName")
    private String name;

    public String getStudentName() {
        return name;
    }

    public void setStudentName(String studentName) {
        this.name = studentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
