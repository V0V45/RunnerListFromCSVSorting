package com.filecreator.creator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContext {
    @Bean
    public ResultsProcessor resultsProcessor() {
        return new ResultsProcessor();
    }
}
