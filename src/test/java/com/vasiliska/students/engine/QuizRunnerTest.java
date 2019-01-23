package com.vasiliska.students.engine;

import com.vasiliska.students.dao.DataReader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class QuizRunnerTest {

    @Test
    public void quizRunTest() {
        QuizRunner quizRunner = new QuizRunner(new DataReader());
        Assert.assertEquals(0, quizRunner.quizRun());
    }

    @Test
    public void filProfile() {
        QuizRunner quizRunner = new QuizRunner(new DataReader());
        List<String> answerProfile = quizRunner.filProfile(new ArrayList<>());
        Assert.assertEquals(0, answerProfile.size());
    }

}