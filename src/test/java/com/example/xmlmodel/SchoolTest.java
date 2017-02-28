package com.example.xmlmodel;

import com.example.utils.ParseXmlUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Package: com.example.xmlmodel
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/2/28 下午5:54
 */
public class SchoolTest {
    public static void main(String[] args) {
        School school = new School();
        school.setName("jianshu:http://www.jianshu.com/u/e210c8fa704f");
        Classes classes = new Classes();
        classes.setClassName("一班");
        classes.setStudentName("小明");

        Classes classes1 = new Classes();
        classes1.setClassName("二班");
        classes1.setStudentName("小红");

        List<Classes> classes2 = new ArrayList<>();
        classes2.add(classes);
        classes2.add(classes1);

        school.setClasses(classes2);
        System.out.println(ParseXmlUtil.parseToXml(school));
    }
}