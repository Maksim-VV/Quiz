package com.vasiliska.students.dao;

import com.vasiliska.students.service.Question;
import lombok.Data;

import java.util.List;


public interface DataReader
{
       String CSV_SPLIT_BY = ";";
       int MIN_NUM_ANSWER = 4;
       int MAX_NUM_ANSWER = 8;
       int NUM_CORRECT_ANSWER = 1;
       int NUM_QUESTION_QUIZ = 2;
       int NUM_STRING_QUESTION = 3;

       List<Question> readData();
       List<Question> getQuestList();
}
