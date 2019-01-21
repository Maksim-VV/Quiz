package com.vasiliska.students.dao;

import com.vasiliska.students.service.Question;
import lombok.Data;
import lombok.val;

import java.io.*;
import java.util.*;

@Data
public class DataReader {
    private String fileName = "quizTestUS.csv";
    private Question quest;
    private List<Question> questList;
    private final String CVS_SPLIT_BY = ";";
    private final int MIN_NUM_ANSWER = 4;
    private final int MAX_NUM_ANSWER = 8;



    public DataReader()
    {
        questList = new ArrayList<>();
    }

    public List<Question> readData() {
        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());

        try (BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"))) {

            String line = null;
            while ((line = buffReader.readLine()) != null) {
                String[] currStr = line.split(CVS_SPLIT_BY);
                quest = new Question();
                quest.setCorrectAnswer(currStr[1]);
                quest.setNumberQuestion(currStr[2]);
                quest.setQuestion(currStr[3]);

                for(int answNum = MIN_NUM_ANSWER; answNum < MAX_NUM_ANSWER; answNum++)
                {
                    quest.answers.add(currStr[answNum]);
                }

                questList.add(quest);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return questList;

    }


}
