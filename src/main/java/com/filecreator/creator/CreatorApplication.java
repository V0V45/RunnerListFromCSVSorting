package com.filecreator.creator;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreatorApplication {

	public static void main(String[] args) {
		try {
			ArrayList<Runner> test = ResultsProcessor.getFastestRunners("base.csv", 3, 10, 'М');
			ResultsProcessor.printRunners(test);
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
		// SpringApplication.run(CreatorApplication.class, args);
	}

}
