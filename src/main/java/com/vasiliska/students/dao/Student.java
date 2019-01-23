package com.vasiliska.students.dao;


import lombok.Data;

@Data
public class Student {

    private String surname;
    private String name;
    private int score;

    public Student() {
    }

}
