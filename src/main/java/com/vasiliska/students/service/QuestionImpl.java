package com.vasiliska.students.service;

import lombok.Data;

@Data
public class QuestionImpl implements Question
{
       private String numberQuestion;
       private String correctAnswer;
       private String question;
       private String answerA;
       private String answerB;
       private String answerC;
       private String answerD;
}
