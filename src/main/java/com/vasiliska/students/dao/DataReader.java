package com.vasiliska.students.dao;

import com.vasiliska.students.service.Question;

import java.util.List;


public interface DataReader
{
       List<Question> readData();
}
