package com.vasiliska.students.engine;

import com.vasiliska.students.dao.DataReader;
import com.vasiliska.students.dao.Student;
import com.vasiliska.students.service.QuestionImpl;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuizRunner {
    private int rightAnswer = 0;

    @Getter
    @Setter
    private List<QuestionImpl> dataQuiz;
    private DataReader data;
    private final String TITLE_QUIZ = "Введите номер верного ответа:";
    private final String SEPARATOR_STR = ";";

    public QuizRunner(DataReader data) {
        this.data = data;
    }

    public List<String> filProfile(List<String> questProfile) {
        List<String> answerProfile = new ArrayList<>();

        for (String strQuest : questProfile) {
            System.out.println(strQuest);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            try {
                answerProfile.add(buffer.readLine());

            } catch (IOException ex) {
                System.err.println("Ошибка заполнения профиля пользователя!");
            }
        }

        System.out.println(TITLE_QUIZ);
        System.out.println();

        return answerProfile;
    }

    public int quizRun() {

        if (data == null || data.getQuestList() == null) {
            return rightAnswer;
        }

        dataQuiz = data.getQuestList();

        for (QuestionImpl questData : dataQuiz) {
            System.out.println(questData.getNumberQuestion() + " " + questData.getQuestion());
            System.out.print(questData.getAnswerA() + SEPARATOR_STR);
            System.out.print(questData.getAnswerB() + SEPARATOR_STR);
            System.out.print(questData.getAnswerC() + SEPARATOR_STR);
            System.out.print(questData.getAnswerD() + SEPARATOR_STR);
            System.out.println();

            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            try {
                String answer = buffer.readLine();
                if (answer.equals(questData.getCorrectAnswer())) {
                    rightAnswer++;
                }
            } catch (IOException ex) {
                System.err.println("Ошибка выплолнения викторины!");
            }

        }

        return rightAnswer;

    }


}
