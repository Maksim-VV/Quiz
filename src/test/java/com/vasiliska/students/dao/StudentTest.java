package com.vasiliska.students.dao;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void testListSize() {
        Student studentTest;
        studentTest = new Student();
        Assert.assertEquals(2, studentTest.listQuest.size());
    }


    @Test
    public void testFirstStrList() {
        Student studentTest;
        studentTest = new Student();
        Assert.assertEquals("Введите свою фамилию:", studentTest.listQuest.get(0));
    }

    @Test
        public void testSecondStrList() {
            Student studentTest;
            studentTest = new Student();
            Assert.assertEquals("Введите своё имя:", studentTest.listQuest.get(1));
        }


}