package com.vasiliska.students.engine;

import com.vasiliska.students.Main;
import com.vasiliska.students.dao.DataReader;
import com.vasiliska.students.dao.Student;
import com.vasiliska.students.service.Question;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Service
public class QuizRunner
{
    private int rightAnswer = 0;
    private List<Question> dataQuiz;
    private List<String> listPersonData;
    private DataReader data;
    private final String SEPARATOR_STR = "; ";
    private String suraname;
    private String name;
    private Student student;
    private MessageSource messageSource;

    @Autowired
    public QuizRunner(DataReader data, MessageSource messageSource)
    {
        this.messageSource = messageSource;
        student = new Student();
        this.data = data;
    }

    public QuizRunner(){};

    public boolean fillProfileStudent()
    {
        setStudentPersonalData();
        List<String> answerProfile = new ArrayList<String>();

        for (String strQuest : listPersonData)
        {
            System.out.println(strQuest);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            try
            {
                answerProfile.add(buffer.readLine());
            }
            catch (IOException ex)
            {
                System.out.println(messageSource.getMessage("errorFillProfile", null, Main.locale));
            }
        }
        System.out.println(messageSource.getMessage("startQuiz", null, Main.locale) + "\n");

        student.setSurname(answerProfile.get(0));
        student.setName(answerProfile.get(1));
        
        return true;
    }

    private void setStudentPersonalData()
    {
        suraname =  messageSource.getMessage("enterSurname", null, Main.locale);
        name =  messageSource.getMessage("enterName", null, Main.locale);
        listPersonData = Arrays.asList(suraname, name);
    }


    public boolean quizRun()
    {
        if (data == null || data.getQuestList() == null)
        {
            return false;
        }

        dataQuiz = data.getQuestList();

        for (Question questData : dataQuiz)
        {
            System.out.println(questData.getNumberQuestion() + " " + questData.getQuestion());

            questData.answers.stream().forEach(v -> System.out.print(v + SEPARATOR_STR));
            System.out.println();

            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            try
            {
                String answer = buffer.readLine();
                if (questData.getCorrectAnswer() != null && answer.equals(questData.getCorrectAnswer()))
                {
                    rightAnswer++;
                }
            }
            catch (IOException ex)
            {
                System.out.println(messageSource.getMessage("errorRunQuiz", null, Main.locale));
            }
        }
        
        student.setScore(rightAnswer);

        return true;
    }

    public void writeTotal(int countQuest)
    {
        System.out.println(messageSource.getMessage("finishQuiz", new String[]{student.getName(), String.valueOf(student.getScore()), String.valueOf(countQuest)}, Main.locale));
    }

}
