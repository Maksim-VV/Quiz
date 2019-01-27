package com.vasiliska.students.service;

import com.vasiliska.students.dao.DataReaderImp;
import com.vasiliska.students.engine.QuizRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@PropertySource("classpath:data.properties")
@Configuration
public class QuizConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    DataReaderImp dataReader(@Value("${db.url}") String dbUrl, MessageSource messageSource)
    {
        return new DataReaderImp(dbUrl, messageSource);
    }

    @Bean
    QuizRunner quizRunner(DataReaderImp dataReader)
    {
        return new QuizRunner(dataReader);
    }


}
