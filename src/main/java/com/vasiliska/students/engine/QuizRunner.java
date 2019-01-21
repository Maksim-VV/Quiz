package com.vasiliska.students.engine;

import com.vasiliska.students.Main;
import com.vasiliska.students.dao.DataReader;
import com.vasiliska.students.dao.Student;
import com.vasiliska.students.service.Question;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import javax.xml.soap.SOAPPart;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class QuizRunner {
    private int rightAnswer = 0;

    @Autowired
    private MessageSource  messageSource;

    @Getter
    @Setter
    private List<Question> dataQuiz;
    private DataReader data;
    private final String SEPARATOR_STR = "; ";

    public QuizRunner(DataReader data) {
        this.data = data;
    }

    public List<String> filProfile(List<String> questProfile)
    {
        List<String> answerProfile = new ArrayList<>();

        for (String strQuest : questProfile)
        {
            System.out.println(strQuest);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            try
            {
               answerProfile.add(buffer.readLine());
            }
            catch (IOException ex)
            {
               System.out.println(messageSource.getMessage("errorFillProfile",null,  Main.locale));
            }
        }
          System.out.println(messageSource.getMessage("startQuiz",null,  Main.locale) + "\n");
        return answerProfile;
    }

    public int quizRun() {

        if (data == null || data.getQuestList() == null)
        {
            return rightAnswer;
        }

        dataQuiz = data.getQuestList();

        for (Question questData : dataQuiz)
        {
            System.out.println(questData.getNumberQuestion() + " " + questData.getQuestion());
            
            questData.answers.stream().forEach(v-> System.out.print(v +  SEPARATOR_STR));
            System.out.println();

            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            try
            {
                String answer = buffer.readLine();
                if (answer.equals(questData.getCorrectAnswer()))
                {
                    rightAnswer++;
                }
            } catch (IOException ex)
            {
                System.out.println(messageSource.getMessage("errorRunQuiz",null,  Main.locale));
            }
        }
        return rightAnswer;
    }

    public void writeTotal(Student student, int countQuest)
    {
        System.out.println(messageSource.getMessage("finishQuiz",new String[]{student.getName(), String.valueOf(student.getScore()), String.valueOf(countQuest)},  Main.locale));
    }





}
