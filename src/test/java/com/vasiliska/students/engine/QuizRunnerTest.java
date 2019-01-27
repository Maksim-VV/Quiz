package com.vasiliska.students.engine;

import com.vasiliska.students.dao.DataReaderImp;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizRunnerTest {

    @Test
    public void quizRunTest()
    {
        QuizRunner quizRunner = new QuizRunner();
        Assert.assertEquals(false, quizRunner.quizRun());
    }
}