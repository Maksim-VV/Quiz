package com.vasiliska.students.dao;

import com.vasiliska.students.service.Question;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class DataReaderTest {

    @Test
    public void readData()
    {
        DataReader dataReader = new DataReaderImp();
        ((DataReaderImp) dataReader).setFileName("en_US");
        ((DataReaderImp) dataReader).setDbUrl("quizTest%s.csv");

        List<Question> listQuestion = dataReader.readData();
        int sizeList =  listQuestion.size();
        Assert.assertEquals(6, sizeList);
    }
}