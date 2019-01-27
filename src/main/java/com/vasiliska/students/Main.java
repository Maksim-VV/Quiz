package com.vasiliska.students;

import com.vasiliska.students.dao.DataReader;
import com.vasiliska.students.dao.DataReaderImp;
import com.vasiliska.students.dao.Student;
import com.vasiliska.students.engine.QuizRunner;
import com.vasiliska.students.service.Question;
import com.vasiliska.students.service.QuizConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.*;

@Slf4j
public class Main
{
    public static Locale locale = Locale.getDefault();

    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext quizAnnatationConfig = new AnnotationConfigApplicationContext();
        quizAnnatationConfig.register(QuizConfig.class);
        quizAnnatationConfig.refresh();

        DataReader data = quizAnnatationConfig.getBean(DataReaderImp.class);

        List<Question> questionsList = null;

        try
        {
            questionsList = data.readData();
        }
        catch (Exception ex) 
        {
            System.out.println(quizAnnatationConfig.getMessage("errorLoadQuiz", null, Main.locale));
            log.error("Error loading the list of questions {}", ex);
        }

        QuizRunner quiz = quizAnnatationConfig.getBean(QuizRunner.class);
        quiz.fillProfileStudent();
        quiz.quizRun();

        int countQuest = 0;

        if (questionsList != null)
        {
            countQuest = questionsList.size();
        }
        quiz.writeTotal(countQuest);
    }

}
