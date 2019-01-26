package com.vasiliska.students.dao;

import com.vasiliska.students.service.Question;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

//@DisplayName()
public class DataReaderTest {

    @Test
    public void readData() {

        DataReader dataReader = new DataReader();
        //dataReader.setFileName("quizTestUS.csv");

        List<Question> listQuestion = dataReader.readData();
        int sizeList =  listQuestion.size();

        Assert.assertEquals(6, sizeList);
    }
}