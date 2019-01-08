package com.vasiliska.students.service;

import com.vasiliska.students.dao.DataReader;
import com.vasiliska.students.dao.Student;
import com.vasiliska.students.engine.QuizRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class QuizConfig {

    @Bean
    DataReader dataReader(@Value("quizTest.csv") String fileName) {
        return new DataReader(fileName);
    }

    @Bean
    Student student() {
        return new Student();
    }

    @Bean
    QuizRunner quizRunner(DataReader dataReader) {
        return new QuizRunner(dataReader);
    }
    
    @Bean
    public MessageSource messageSource()
    {
     ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
     ms.setBasename("/i18n/bundle");
     ms.setDefaultEncoding("UTF-8");
     return ms;
    }

    

}
