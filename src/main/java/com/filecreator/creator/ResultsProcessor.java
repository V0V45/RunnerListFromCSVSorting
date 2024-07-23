package com.filecreator.creator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ResultsProcessor {
    private static ArrayList<Runner> convertCSVtoRunnersArray(String csvFilePath) throws IOException {
        ArrayList<Runner> runners = new ArrayList<Runner>();
        BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
        String currentLine = br.readLine();
        while (currentLine != null) {
            String[] words = currentLine.split(";");
            String currentSurname = words[0];
            String currentName = words[1];
            char currentGender = words[2].charAt(0);
            int currentDistance = Integer.parseInt((words[3].split(" "))[0]);
            String[] currentTimeArray = words[4].split(":");
            int currentTimeMinutes = Integer.parseInt(currentTimeArray[0]);
            int currentTimeSeconds = Integer.parseInt(currentTimeArray[1]);
            int currentTime = (currentTimeMinutes * 60) + currentTimeSeconds;
            Runner currentRunner = new Runner(currentSurname, currentName, currentGender, currentDistance, currentTime);
            runners.add(currentRunner);
            currentLine = br.readLine();
        }
        return runners;
    }

    private static void sortRunnersByFastestOnes(ArrayList<Runner> runnersList) {
        Collections.sort(runnersList);
    }

    private static void setGender(ArrayList<Runner> runnersList, char gender) {
        if (gender == 'М' || gender == 'Ж') {
            for (int i = 0; i < runnersList.size(); i++) {
                if (runnersList.get(i).getGender() != gender) {
                    runnersList.remove(i);
                } else {
                    continue;
                }
            }
        } else {
            throw new IllegalArgumentException("Пол может быть только М или Ж");
        }
    }

    private static void setDistance(ArrayList<Runner> runnersList, int distance) {
        if (distance == 5 || distance == 10) {
            for (int i = 0; i < runnersList.size(); i++) {
                if (runnersList.get(i).getDistance() != distance) {
                    runnersList.remove(i);
                } else {
                    continue;
                }
            }
        } else {
            throw new IllegalArgumentException("Дистанция может быть только 5 или 10 км");
        }
    }

    private static void cutListToLength(ArrayList<Runner> runnersList, int targetRunnersNumber) {
        if (targetRunnersNumber >= runnersList.size()) {
            throw new IllegalArgumentException("Нельзя удалить бегунов больше, чем их всего");
        } else {
            runnersList.subList(targetRunnersNumber, runnersList.size()).clear();
        }
    }

    public static void printRunners(ArrayList<Runner> runnersList) {
        for (Runner runner : runnersList) {
            System.out.println(runner.toString());
        }
    }

    public static ArrayList<Runner> getFastestRunners(String csvFilePath, int targetRunnersNumber, int targetDistance) throws IOException {
        ArrayList<Runner> runners = ResultsProcessor.convertCSVtoRunnersArray(csvFilePath);
        ResultsProcessor.setDistance(runners, targetDistance);
        ResultsProcessor.sortRunnersByFastestOnes(runners);
        ResultsProcessor.cutListToLength(runners, targetRunnersNumber);
        return runners;
    }

    public static ArrayList<Runner> getFastestRunners(String csvFilePath, int targetRunnersNumber, char targetGender) throws IOException {
        ArrayList<Runner> runners = ResultsProcessor.convertCSVtoRunnersArray(csvFilePath);
        ResultsProcessor.setGender(runners, targetGender);
        ResultsProcessor.sortRunnersByFastestOnes(runners);
        ResultsProcessor.cutListToLength(runners, targetRunnersNumber);
        return runners;
    }

    public static ArrayList<Runner> getFastestRunners(String csvFilePath, int targetRunnersNumber, int targetDistance, char targetGender) throws IOException {
        ArrayList<Runner> runners = ResultsProcessor.convertCSVtoRunnersArray(csvFilePath);
        ResultsProcessor.setGender(runners, targetGender);
        ResultsProcessor.setDistance(runners, targetDistance);
        ResultsProcessor.sortRunnersByFastestOnes(runners);
        ResultsProcessor.cutListToLength(runners, targetRunnersNumber);
        return runners;
    }
}