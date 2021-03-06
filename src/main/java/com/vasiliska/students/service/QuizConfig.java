package com.vasiliska.students.service;

import com.vasiliska.students.dao.DataReader;
import com.vasiliska.students.dao.DataReaderImp;
import com.vasiliska.students.engine.QuizRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@PropertySource("classpath:data.properties")
@Configuration
public class QuizConfig {

    public Locale locale = Locale.getDefault();

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
    DataReader dataReader(@Value("${db.url}") String dbUrl, MessageSource messageSource)
    {
        String fileName = messageSource.getMessage("fileName", null, locale);
        fileName = String.format(dbUrl, fileName);
        return new DataReaderImp(fileName, messageSource);
    }

    @Bean
    QuizRunner quizRunner(MessageSource messageSource) {
        return new QuizRunner(messageSource, locale);
    }


}
