package com.vasiliska.students.dao;

import com.vasiliska.students.service.Question;
import lombok.Data;

import java.util.List;


public interface DataReader
{
       List<Question> readData();
       List<Question> getQuestList();
}
