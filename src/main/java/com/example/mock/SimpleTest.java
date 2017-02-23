package com.example.mock;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.mongodb.MongoDbFactory;

import java.util.List;

/**
 * Created by liuxin on 17/1/13.
 * mock测试
 */
public class SimpleTest {
    @Test
    public void simpleTest() {
        List<String> list = mock(List.class);
        when(list.get(0)).thenReturn("hellworld");
        String result = list.get(0);
        Assert.assertEquals("hellworld", result);
        System.out.println(result);
    }

    @Test
    public void testTeacher() {
        Teacher teacher = mock(Teacher.class);
        when(teacher.getAge()).thenReturn(45);
        System.out.println(teacher.getAge());
    }

}
