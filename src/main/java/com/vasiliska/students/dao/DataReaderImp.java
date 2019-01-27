package com.vasiliska.students.dao;

import com.vasiliska.students.Main;
import com.vasiliska.students.service.Question;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
public class DataReaderImp implements DataReader {
    private String fileName;
    private MessageSource messageSource;
    private String dbUrl;
    private Question quest;
    private List<Question> questList;
    private String CSV_SPLIT_BY = ";";
    private final int MIN_NUM_ANSWER = 4;
    private final int MAX_NUM_ANSWER = 8;
    private final int NUM_CORRECT_ANSWER = 1;
    private final int NUM_QUESTION_QUIZ = 2;
    private final int NUM_STRING_QUESTION = 3;

    @Autowired
    public DataReaderImp(String dbUrl, MessageSource messageSource) {
        this.messageSource = messageSource;
        this.dbUrl = dbUrl;
        questList = new ArrayList<>();
        this.fileName = messageSource.getMessage("fileName", null, Main.locale);
    }

    public DataReaderImp() {
        questList = new ArrayList<>();
    }

    public List<Question> readData() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(String.format(dbUrl, fileName)).getFile());

        try (BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"))) {
            String line = null;
            while ((line = buffReader.readLine()) != null) {
                String[] currStr = line.split(CSV_SPLIT_BY);
                quest = new Question();
                quest.setCorrectAnswer(currStr[NUM_CORRECT_ANSWER]);
                quest.setNumberQuestion(currStr[NUM_QUESTION_QUIZ]);
                quest.setQuestion(currStr[NUM_STRING_QUESTION]);

                for (int answNum = MIN_NUM_ANSWER; answNum < MAX_NUM_ANSWER; answNum++) {
                    quest.answers.add(currStr[answNum]);
                }
                questList.add(quest);
            }
        } catch (IOException e) {
            log.error("Error run {}", e);
        }
        return questList;
    }

}
