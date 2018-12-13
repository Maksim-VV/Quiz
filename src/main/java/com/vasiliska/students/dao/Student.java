package com.vasiliska.students.dao;


import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Student implements StudentProfile {

    String surname;
    String name;
    int score;

    List<String> listQuest = Arrays.asList(questionSurname, questionName);

    public Student() {
    }

}
