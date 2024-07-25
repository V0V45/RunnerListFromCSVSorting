package com.filecreator.creator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ApplicationContext {
    @Bean
    public ResultsProcessor resultsProcessor() {
        ResultsProcessor processor = new ResultsProcessor("base.csv");
        processor.setTargetDistance(10);
        processor.setTargetGender('Ж');
        return processor;
    }
}
