package com.vasiliska.students.engine;

import com.vasiliska.students.dao.DataReader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizRunnerTest {

    @Test
    public void quizRunTest()
    {
        QuizRunner quizRunner = new QuizRunner(new DataReader());
        Assert.assertEquals(0, quizRunner.quizRun());
    }

    @Test
    public void fillProfile() 
    {
        QuizRunner quizRunner = new QuizRunner(new DataReader());
        quizRunner.setListPersonData(new ArrayList<String>());
        List<String> answerProfile = quizRunner.fillProfile();
        Assert.assertEquals(0, answerProfile.size());
    }

}