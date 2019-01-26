package com.vasiliska.students.dao;

import com.vasiliska.students.Main;
import com.vasiliska.students.service.Question;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;

import java.io.*;
import java.util.*;

@Slf4j
@Data
public class DataReader  implements DataReadable
{
    @Value("${db.url}")
    private String dbUrl;
    @Autowired
    private MessageSource messageSource;
    private Question quest;
    private List<Question> questList;

    public DataReader()
    {
        questList = new ArrayList<>();
    }

    public List<Question> readData()
    {
        ClassLoader classLoader = getClass().getClassLoader();
        String fileName = messageSource.getMessage("fileName", null, Main.locale);

        File file = new File(classLoader.getResource(String.format(dbUrl,fileName)).getFile());

        try (BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8")))
        {
            String line = null;
            while ((line = buffReader.readLine()) != null)
            {
                String[] currStr = line.split(CSV_SPLIT_BY);
                quest = new Question();
                quest.setCorrectAnswer(currStr[NUM_CORRECT_ANSWER]);
                quest.setNumberQuestion(currStr[NUM_QUESTION_QUIZ]);
                quest.setQuestion(currStr[NUM_STRING_QUESTION]);

                for (int answNum = MIN_NUM_ANSWER; answNum < MAX_NUM_ANSWER; answNum++)
                {
                    quest.answers.add(currStr[answNum]);
                }
                questList.add(quest);
            }
        }
        catch (IOException e)
        {
            log.error("Error run {}", e);
        }
        return questList;
    }

}
