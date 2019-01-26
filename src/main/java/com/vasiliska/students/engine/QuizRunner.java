package com.vasiliska.students.engine;

import com.vasiliska.students.Main;
import com.vasiliska.students.dao.DataReadable;
import com.vasiliska.students.dao.DataReader;
import com.vasiliska.students.dao.Student;
import com.vasiliska.students.service.Question;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.xml.soap.SOAPPart;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
@Service
public class QuizRunner
{
    private int rightAnswer = 0;

    @Autowired
    private MessageSource messageSource;
    private List<Question> dataQuiz;
    private List<String> listPersonData;
    private DataReadable data;
    private final String SEPARATOR_STR = "; ";
    private String suraname;
    private String name;

    public QuizRunner(DataReadable data)
    {
        this.data = data;
    }

    public void setSuraname()
    {
        suraname =  messageSource.getMessage("enterSurname", null, Main.locale);
    }

    public void setName()
    {
        name =  messageSource.getMessage("enterName", null, Main.locale);
    }

    public void setPersonData()
    {
        listPersonData = Arrays.asList(suraname, name);
    }



    public List<String> fillProfile()
    {
        List<String> answerProfile = new ArrayList<String>();

        if(listPersonData==null || listPersonData.isEmpty())
        {
            return answerProfile;
        }

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
        return answerProfile;
    }

    public int quizRun()
    {
        if (data == null || data.getQuestList() == null)
        {
            return rightAnswer;
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
        return rightAnswer;
    }

    public void writeTotal(Student student, int countQuest)
    {
        System.out.println(messageSource.getMessage("finishQuiz", new String[]{student.getName(), String.valueOf(student.getScore()), String.valueOf(countQuest)}, Main.locale));
    }

}
