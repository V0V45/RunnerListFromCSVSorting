package com.filecreator.creator;

import java.io.IOException;
import java.util.ArrayList;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LaunchTest {
    public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContext.class);
        try {
			ResultsProcessor processor = context.getBean(ResultsProcessor.class);
            ArrayList<Runner> testList1 = processor.getFastestRunnersByDistance(10);
            ArrayList<Runner> testList2 = processor.getFastestRunnersByGender(3);
            ArrayList<Runner> testList3 = processor.getFastestRunnersByGenderAndDistance(5);
            System.out.println("---");
            processor.printRunners(testList1);
            System.out.println("---");
            processor.printRunners(testList2);
            System.out.println("---");
            processor.printRunners(testList3);
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
		context.close();
    }
    
}
