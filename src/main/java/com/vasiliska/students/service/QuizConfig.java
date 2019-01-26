package com.vasiliska.students.service;

import com.vasiliska.students.dao.DataReader;
import com.vasiliska.students.dao.Student;
import com.vasiliska.students.engine.QuizRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@PropertySource("classpath:data.properties")
@Configuration
public class QuizConfig
{
    @Bean
    public MessageSource messageSource()
    {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    DataReader dataReader()
    {
        return new DataReader();
    }

    @Bean
    QuizRunner quizRunner(DataReader dataReader)
    {
        return new QuizRunner(dataReader);
    }


}
