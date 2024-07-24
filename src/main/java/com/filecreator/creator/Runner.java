package com.filecreator.creator;

// Класс бегуна
public class Runner implements Comparable<Runner> { // имплементируем Comparable для возможности сортировки по времени
    // Поля класса:
    private String surname; // фамилия бегуна
    private String name; // имя бегуна
    private char gender; // пол бегуна
    private int distance; // дистанция, которую пробежал бегун
    private int time; // время в секундах, которую потратил бегун на дистанцию

    // Конструктор:
    public Runner(String surname, String name, char gender, int distance, int time) {
        this.surname = surname;
        this.name = name;
        // пол может быть только М и Ж, иначе ошибка
        if (gender == 'М' || gender == 'Ж') {
            this.gender = gender;
        } else {
            throw new IllegalArgumentException("Пол может быть М или Ж");
        }
        // дистанция может быть только 5 или 10 км, иначе ошибка
        if (distance == 5 || distance == 10) {
            this.distance = distance;
        } else {
            throw new IllegalArgumentException("Дистанция может быть 5 или 10 км");
        }
        this.time = time;
    }

    // Геттеры:
    public String getSurname() {
        return this.surname;
    }

    public String getName() {
        return this.name;
    }

    public char getGender() {
        return this.gender;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getTime() {
        return this.time;
    }

    // Метод toSting:
    @Override
    public String toString() {
        return "{" +
            " surname='" + getSurname() + "'" +
            ", name='" + getName() + "'" +
            ", gender='" + getGender() + "'" +
            ", distance='" + getDistance() + "'" +
            ", time='" + getTime() + "'" +
            "}";
    }

    // Для сортировки используем перезапись метода compareTo интерфейса Comparable
    @Override
    public int compareTo(Runner o) {
        return this.time - o.time;
    }
}
