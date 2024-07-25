package com.filecreator.creator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import jakarta.annotation.PostConstruct;

// Класс, реализующий методы работы над списком бегунов
public class ResultsProcessor {
    // Метод, запускающийся автоматически после создания объекта класса
    @PostConstruct
    public void init() {
        try {
            ArrayList<Runner> test = getFastestRunners("base.csv", 5, 10, 'М');
            printRunners(test);
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    // Метод, позволяющий прочитать CSV файл и его содержимое преобразовать в
    // объекты класса Runner
    private ArrayList<Runner> convertCSVtoRunnersArray(String csvFilePath) throws IOException {
        ArrayList<Runner> runners = new ArrayList<Runner>(); // создаем пустой массив с бегунами
        BufferedReader br = new BufferedReader(new FileReader(csvFilePath)); // создаем BufferedReader для чтения файла
                                                                             // CSV
        String currentLine = br.readLine(); // построчно читаем файл и передаем данные в переменную currentLine
        while (currentLine != null) { // пока текущая строка не станет пустой
            String[] words = currentLine.split(";"); // создаем массив, разбивающий строку из файла CSV на слова.
                                                     // Разделитель - точка с запятой
            String currentSurname = words[0]; // фамилия есть первое слово в строке
            String currentName = words[1]; // имя есть второе слово в строке
            char currentGender = words[2].charAt(0); // пол есть третье слово в строке. Переводим в char с помощью
                                                     // charAt
            int currentDistance = Integer.parseInt((words[3].split(" "))[0]); // дистанция есть четвертое слово в строке
            String[] currentTimeArray = words[4].split(":"); // время нужно правильно перевести, поэтому создаем еще
                                                             // один массив,
            // который представляет собой два слова, разделенных двоеточием (по сути, минуты
            // и секунды)
            int currentTimeMinutes = Integer.parseInt(currentTimeArray[0]); // минуты есть первое слово
            int currentTimeSeconds = Integer.parseInt(currentTimeArray[1]); // секунды есть второе слово
            int currentTime = (currentTimeMinutes * 60) + currentTimeSeconds; // переводим время полностью в секунды
            Runner currentRunner = new Runner(currentSurname, currentName, currentGender, currentDistance, currentTime); // создаем
                                                                                                                         // объект
                                                                                                                         // бегуна
            runners.add(currentRunner); // добавляем бегуна в общий массив
            currentLine = br.readLine(); // переходим к следующей строчке файла CSV
        }
        br.close(); // по окончанию цикла закрываем BufferedReader
        return runners; // возвращаем массив с бегунами как результат работы метода
    }

    // Метод, позволяющий сортировать бегунов в массиве от самого быстрого к самому
    // медленному
    private void sortRunnersByFastestOnes(ArrayList<Runner> runnersList) {
        Collections.sort(runnersList);
    }

    // Метод, позволяющий оставить в массиве бегунов только один указанный пол
    private void setGender(ArrayList<Runner> runnersList, char gender) {
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

    // Метод, позволяющий оставить в массиве бегунов только одну указанную дистанцию
    private void setDistance(ArrayList<Runner> runnersList, int distance) {
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

    // Метод, позволяющий сократить массив бегунов до заданной величины
    private void cutListToLength(ArrayList<Runner> runnersList, int targetRunnersNumber) {
        if (targetRunnersNumber >= runnersList.size()) {
            throw new IllegalArgumentException("Нельзя удалить бегунов больше, чем их всего");
        } else {
            runnersList.subList(targetRunnersNumber, runnersList.size()).clear();
        }
    }

    // Метод, позволяющий вывести массив бегунов в консоль
    public void printRunners(ArrayList<Runner> runnersList) {
        for (Runner runner : runnersList) {
            System.out.println(runner.toString());
        }
    }

    // Метод, преобразующий CSV-файл в отсортированные результаты по количеству
    // бегунов и дистанции
    public ArrayList<Runner> getFastestRunners(String csvFilePath, int targetRunnersNumber, int targetDistance)
            throws IOException {
        ArrayList<Runner> runners = convertCSVtoRunnersArray(csvFilePath);
        setDistance(runners, targetDistance);
        sortRunnersByFastestOnes(runners);
        cutListToLength(runners, targetRunnersNumber);
        return runners;
    }

    // Метод, преобразующий CSV-файл в отсортированные результаты по количеству
    // бегунов и полу
    public ArrayList<Runner> getFastestRunners(String csvFilePath, int targetRunnersNumber, char targetGender)
            throws IOException {
        ArrayList<Runner> runners = convertCSVtoRunnersArray(csvFilePath);
        setGender(runners, targetGender);
        sortRunnersByFastestOnes(runners);
        cutListToLength(runners, targetRunnersNumber);
        return runners;
    }

    // Метод, преобразующий CSV-файл в отсортированные результаты по количеству
    // бегунов, дистанции и полу
    public ArrayList<Runner> getFastestRunners(String csvFilePath, int targetRunnersNumber, int targetDistance,
            char targetGender) throws IOException {
        ArrayList<Runner> runners = convertCSVtoRunnersArray(csvFilePath);
        setGender(runners, targetGender);
        setDistance(runners, targetDistance);
        sortRunnersByFastestOnes(runners);
        cutListToLength(runners, targetRunnersNumber);
        return runners;
    }
}