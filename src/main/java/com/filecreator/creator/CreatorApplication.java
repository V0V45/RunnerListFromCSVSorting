package com.filecreator.creator;


import java.io.IOException;
import java.util.ArrayList;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class CreatorApplication {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContext.class);
        try {
			ResultsProcessor processor = context.getBean(ResultsProcessor.class);
            ArrayList<Runner> testList = processor.getFastestRunnersByGenderAndDistance(10);
            processor.printRunners(testList);
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
		context.close();
	}

}
