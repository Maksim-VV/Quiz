package com.vasiliska.students.dao;

import com.vasiliska.students.service.Question;
import com.vasiliska.students.service.QuestionImpl;
import lombok.Data;
import lombok.val;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;

import java.io.*;
import java.util.*;

@Data
public class DataReader {
    private String fileName = "quizTest.csv";
    private QuestionImpl quest;
    private List<QuestionImpl> questList;
    private String line = null;
    private final String cvsSplitBy = ";";

    public DataReader() {
    }

    public DataReader(String fileName) {
        this.fileName = fileName;
        questList = new ArrayList<>();
    }

    public List<QuestionImpl> readData() {
        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());

        try (BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"))) {

            while ((line = buffReader.readLine()) != null) {
                String[] currStr = line.split(cvsSplitBy);
                quest = new QuestionImpl();
                quest.setCorrectAnswer(currStr[1]);
                quest.setNumberQuestion(currStr[2]);
                quest.setQuestion(currStr[3]);
                quest.setAnswerA(currStr[4]);
                quest.setAnswerB(currStr[5]);
                quest.setAnswerC(currStr[6]);
                quest.setAnswerD(currStr[7]);
                questList.add(quest);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return questList;

    }


}
