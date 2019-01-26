package com.vasiliska.students.dao;

import com.vasiliska.students.service.Question;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;

@Slf4j
@Data
public class DataReader  implements DataReadable
{
    private Question quest;
    private List<Question> questList;
    String fileName = null;

    public DataReader()
    {
        questList = new ArrayList<>();
    }

    public List<Question> readData()
    {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

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


    public void setFileName()
    {
        this.fileName = fileName;
    }

}
