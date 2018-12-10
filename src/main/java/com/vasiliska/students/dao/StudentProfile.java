package com.vasiliska.students.dao;

import lombok.Data;

import java.util.Arrays;
import java.util.List;


public interface StudentProfile {
    String surname = null;
    String name = null;
    int score = 0;

    String questionSurname = "Введите свою фамилию:";
    String questionName = "Введите своё имя:";
}
