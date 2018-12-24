package com.vasiliska.students.dao;


import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Student  {

    String surname;
    String name;
    int score;

    final String  QUESTION_SURANAME = "Введите свою фамилию:";
    final String QUESTION_NAME = "Введите своё имя:";
    final List<String> listQuest;

    public Student() {
        listQuest = Arrays.asList(QUESTION_SURANAME, QUESTION_NAME);
    }

}
