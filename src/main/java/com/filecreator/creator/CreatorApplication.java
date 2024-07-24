package com.filecreator.creator;

import java.io.IOException;
import java.util.ArrayList;

public class CreatorApplication {

	public static void main(String[] args) {
		// TODO: Прицепить Spring, тесты написать
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
