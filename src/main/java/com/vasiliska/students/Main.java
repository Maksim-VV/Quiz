package com.vasiliska.students;

import com.vasiliska.students.dao.DataReader;
import com.vasiliska.students.dao.DataReaderImp;
import com.vasiliska.students.engine.QuizRunner;
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
        QuizRunner quiz = quizAnnatationConfig.getBean(QuizRunner.class);

        quiz.loadQuizData(data);
        quiz.fillProfileStudent();
        quiz.quizRun();
        quiz.writeTotal();
    }

}
