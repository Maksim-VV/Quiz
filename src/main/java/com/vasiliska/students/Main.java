package com.vasiliska.students;

import com.vasiliska.students.dao.DataReader;
import com.vasiliska.students.dao.Student;
import com.vasiliska.students.engine.QuizRunner;
import com.vasiliska.students.service.Question;
import com.vasiliska.students.service.QuizConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.util.*;

public class Main
{

    public static void main(String[] args) {

        AnnotationConfigApplicationContext QuizAnnatationConfig = new AnnotationConfigApplicationContext();

        QuizAnnatationConfig.register(QuizConfig.class);
        QuizAnnatationConfig.refresh();

        System.out.println(QuizAnnatationConfig.getMessage("errorFillProfile",null,  new Locale("en")));
        System.out.println(QuizAnnatationConfig);
       // System.out.println(messageSource);

        DataReader data = QuizAnnatationConfig.getBean(DataReader.class);
        Student student = QuizAnnatationConfig.getBean(Student.class);

        List<Question> questionsList = null;

        try
        {
            questionsList = data.readData();
        }
        catch (Exception ex)
        {
            System.err.println("Ошибка загрузки списка вопросов.");
        }

        QuizRunner quiz = QuizAnnatationConfig.getBean(QuizRunner.class);

        List<String> profileData = quiz.filProfile(student.getListQuest());

        student.setSurname(profileData.get(0));
        student.setName(profileData.get(1));
        student.setScore(quiz.quizRun());



        int countQuest = 0;

        if (questionsList != null) {
            countQuest = questionsList.size();
        }
        
        quiz.writeTotal(student, countQuest);

    }

}
