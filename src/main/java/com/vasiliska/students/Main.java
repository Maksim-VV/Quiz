package com.vasiliska.students;

import com.vasiliska.students.dao.DataReader;
import com.vasiliska.students.dao.Student;
import com.vasiliska.students.engine.QuizRunner;
import com.vasiliska.students.service.Question;
import com.vasiliska.students.service.QuestionImpl;
import lombok.val;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.*;

public class Main {

    private static final String totalQuizStr = "%s вы ответили правильно на %d из %d вопросов.";

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        DataReader data = (DataReader) context.getBean("dataReader");
        Student student = (Student) context.getBean("student");

        List<QuestionImpl> questionsList = null;

        try {
            questionsList = data.readData();
        } catch (Exception ex) {
            System.err.println("Ошибка загрузки списка вопросов.");
        }

        QuizRunner quiz = (QuizRunner) context.getBean("quizRunner");

        List<String> profileData = quiz.filProfile(student.getListQuest());

        student.setSurname(profileData.get(0));
        student.setName(profileData.get(1));
        student.setScore(quiz.quizRun());

        int countQuest = 0;

        if (questionsList != null) {
            countQuest = questionsList.size();
        }

        System.out.println(String.format(totalQuizStr, student.getName(), student.getScore(), countQuest));
    }

}
