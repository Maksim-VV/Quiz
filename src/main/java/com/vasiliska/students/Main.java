package com.vasiliska.students;

import com.vasiliska.students.dao.DataReader;
import com.vasiliska.students.dao.Student;
import com.vasiliska.students.engine.QuizRunner;
import com.vasiliska.students.service.Question;
import com.vasiliska.students.service.QuizConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.*;

@Slf4j
public class Main {

    public static Locale locale = Locale.getDefault();

    public static void main(String[] args) {
        AnnotationConfigApplicationContext QuizAnnatationConfig = new AnnotationConfigApplicationContext();

        QuizAnnatationConfig.register(QuizConfig.class);
        QuizAnnatationConfig.refresh();
        
        String fileName = QuizAnnatationConfig.getMessage("fileName", null, Main.locale);

        DataReader data = QuizAnnatationConfig.getBean(DataReader.class);
        data.setFileName(fileName);
        Student student = QuizAnnatationConfig.getBean(Student.class);

        List<Question> questionsList = null;

        try {
            questionsList = data.readData();
        } catch (Exception ex) {
            System.out.println(QuizAnnatationConfig.getMessage("errorLoadQuiz", null, Main.locale));
            log.error("Error loading the list of questions {}", ex);
        }

        QuizRunner quiz = QuizAnnatationConfig.getBean(QuizRunner.class);

        final String QUESTION_SURANAME = QuizAnnatationConfig.getMessage("enterSurname", null, Main.locale);
        final String QUESTION_NAME = QuizAnnatationConfig.getMessage("enterName", null, Main.locale);

        final List<String> listQuest = Arrays.asList(QUESTION_SURANAME, QUESTION_NAME);

        List<String> profileData = quiz.filProfile(listQuest);

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
