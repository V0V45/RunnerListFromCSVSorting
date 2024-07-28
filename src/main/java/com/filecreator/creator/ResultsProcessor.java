package com.filecreator.creator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

// Класс, реализующий методы работы над списком бегунов
// Объект класса ResultsProcessor содержит в себе информацию о сортировке списка
public class ResultsProcessor {
    // Поля
    private String targetCSVFilePath;
    private char targetGender;
    private int targetDistance;

    // Конструктор
    public ResultsProcessor(String targetCSVFilePath) {
        this.targetCSVFilePath = targetCSVFilePath;
    }

    // Геттеры
    public String getTargetCSVFilePath() {
        return this.targetCSVFilePath;
    }

    public char getTargetGender() {
        return this.targetGender;
    }

    public int getTargetDistance() {
        return this.targetDistance;
    }

    // Сеттеры
    public void setTargetCSVFilePath(String targetCSVFilePath) {
        this.targetCSVFilePath = targetCSVFilePath;
    }

    public void setTargetGender(char targetGender) {
        if (targetGender == 'М' || targetGender == 'Ж') {
            this.targetGender = targetGender;
        } else {
            throw new IllegalArgumentException("Пол может быть только М или Ж!");
        }
    }

    public void setTargetDistance(int targetDistance) {
        if (targetDistance == 5 || targetDistance == 10) {
            this.targetDistance = targetDistance;
        } else {
            throw new IllegalArgumentException("Дистанция может быть только 5 или 10 км!");
        }
    }

    // Методы
    // Метод, позволяющий прочитать CSV файл и его содержимое преобразовать в
    // объекты класса Runner
    private ArrayList<Runner> convertCSVtoRunnersArray() throws IOException {
        ArrayList<Runner> runners = new ArrayList<Runner>(); // создаем пустой массив с бегунами
        BufferedReader br = new BufferedReader(new FileReader(this.targetCSVFilePath)); // создаем BufferedReader для
                                                                                        // чтения файла
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
    private void sortByGender(ArrayList<Runner> runnersList) {
        if (this.targetGender != '\u0000') {
            for (int i = 0; i < runnersList.size(); i++) {
                if (runnersList.get(i).getGender() != this.targetGender) {
                    runnersList.remove(i);
                    i = 0;
                } else {
                    continue;
                }
            }
        } else {
            throw new IllegalArgumentException("Пол не задан через сеттер!");
        }
    }

    // Метод, позволяющий оставить в массиве бегунов только одну указанную дистанцию
    private void sortByDistance(ArrayList<Runner> runnersList) {
        if (this.targetDistance != 0) {
            for (int i = 0; i < runnersList.size(); i++) {
                if (runnersList.get(i).getDistance() != this.targetDistance) {
                    runnersList.remove(i);
                    i = 0;
                } else {
                    continue;
                }
            }
        } else {
            throw new IllegalArgumentException("Дистанция не задана через сеттер!");
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
    // бегунов и дистанции. Через сеттер должно быть задано поле targetDistance
    public ArrayList<Runner> getFastestRunnersByDistance(int targetRunnersNumber)
            throws IOException {
        ArrayList<Runner> runners = convertCSVtoRunnersArray();
        sortByDistance(runners);
        sortRunnersByFastestOnes(runners);
        cutListToLength(runners, targetRunnersNumber);
        return runners;
    }

    // Метод, преобразующий CSV-файл в отсортированные результаты по количеству
    // бегунов и полу. Через сеттер должно быть задано поле targetGender
    public ArrayList<Runner> getFastestRunnersByGender(int targetRunnersNumber)
            throws IOException {
        ArrayList<Runner> runners = convertCSVtoRunnersArray();
        sortByGender(runners);
        sortRunnersByFastestOnes(runners);
        cutListToLength(runners, targetRunnersNumber);
        return runners;
    }

    // Метод, преобразующий CSV-файл в отсортированные результаты по количеству
    // бегунов, дистанции и полу. Через сеттер должны быть заданы поля targetGender
    // и targetDistance!
    public ArrayList<Runner> getFastestRunnersByGenderAndDistance(int targetRunnersNumber) throws IOException {
        ArrayList<Runner> runners = convertCSVtoRunnersArray();
        sortByDistance(runners);
        sortByGender(runners);
        sortRunnersByFastestOnes(runners);
        cutListToLength(runners, targetRunnersNumber);
        return runners;
    }

    @Override
    public String toString() {
        return "{" +
            " targetCSVFilePath='" + getTargetCSVFilePath() + "'" +
            ", targetGender='" + getTargetGender() + "'" +
            ", targetDistance='" + getTargetDistance() + "'" +
            "}";
    }

}