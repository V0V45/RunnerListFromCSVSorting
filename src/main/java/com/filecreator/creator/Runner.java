package com.filecreator.creator;

public class Runner implements Comparable<Runner> {
    private String surname;
    private String name;
    private char gender;
    private int distance;
    private int time; // время в секундах

    public Runner(String surname, String name, char gender, int distance, int time) {
        this.surname = surname;
        this.name = name;
        if (gender == 'М' || gender == 'Ж') {
            this.gender = gender;
        } else {
            throw new IllegalArgumentException("Пол может быть М или Ж");
        }
        if (distance == 5 || distance == 10) {
            this.distance = distance;
        } else {
            throw new IllegalArgumentException("Дистанция может быть 5 или 10 км");
        }
        this.distance = distance;
        this.time = time;
    }

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

    @Override
    public int compareTo(Runner o) {
        return this.time - o.time;
    }
}
