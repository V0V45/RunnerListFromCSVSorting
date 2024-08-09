package com.filecreator.creator;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Unit-тесты
class ResultsProcessorTest {
    // поле для создания объекта класса Results Processor
    private ResultsProcessor resultsProcessor;

    // перед запуском любого из тестов нам нужен объект класса Results Processor с параметрами
    // пусть будет пол - Ж, дистанция - 10 км
    @BeforeEach
    void init() {
        resultsProcessor = new ResultsProcessor("base.csv");
        resultsProcessor.setTargetDistance(10);
        resultsProcessor.setTargetGender('Ж');
    }

    /* ТЕСТЫ ДЛЯ МЕТОДА GET FASTEST RUNNERS BY GENDER AND DISTANCE */
    // Тест проверяет, что если попробовать вывести список из бегунов больше, чем их останется после сортировки,
    // то должна вылезти ошибка IllegalArgumentException
    @Test
    void testGetFastestRunnersByGenderAndDistanceMethod_shouldThrowIllegalArgumentException() throws IOException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> resultsProcessor.getFastestRunnersByGenderAndDistance(15));
    }

    // Тест проверяет, все ли отсортированные бегуны будут иметь один и тот же пол
    // в данном случае сортируем по женскому полу, так как мы его установили в самом начале
    @Test
    void testGetFastestRunnersByGenderAndDistanceMethod_shouldShowOnlyFemaleRunners() throws IOException {
        ArrayList<Runner> femaleRunners = resultsProcessor.getFastestRunnersByGenderAndDistance(5);
        boolean ifFemaleOnly = false;
        for (Runner runner : femaleRunners) {
            if (runner.getGender() == 'Ж') {
                ifFemaleOnly = true;
            } else {
                ifFemaleOnly = false;
            }
        }
        Assertions.assertTrue(ifFemaleOnly);
    }

    // Тест проверяет, правильно ли отрезается список
    // указываем, что список нужно сократить до трех человек
    @Test
    void testGetFastestRunnersByGenderAndDistanceMethod_shouldShowThreeRunners() throws IOException {
        ArrayList<Runner> threeRunners = resultsProcessor.getFastestRunnersByGenderAndDistance(3);
        Assertions.assertEquals(3, threeRunners.size());
    }

    // Тест проверяет, все ли отсортированные бегуны будут иметь нужную дистанцию
    // в данном случае сортируем по 10 км, так как мы установили это значение в самом начале
    @Test
    void testGetFastestRunnersByGenderAndDistanceMethod_shouldShowOnlyTenKmDistanceRunners() throws IOException {
        ArrayList<Runner> tenKmRunners = resultsProcessor.getFastestRunnersByGenderAndDistance(5);
        boolean ifTenKmOnly = false;
        for (Runner runner : tenKmRunners) {
            if (runner.getDistance() == 10) {
                ifTenKmOnly = true;
            } else {
                ifTenKmOnly = false;
            }
        }
        Assertions.assertTrue(ifTenKmOnly);
    }

    // Тест проверяет, все ли отсортированные бегуны будут идти от самого быстрого к самому медленному
    @Test
    void testGetFastestRunnersByGenderAndDistanceMethod_shouldShowSortedRunners() throws IOException {
        ArrayList<Runner> fiveRunners = resultsProcessor.getFastestRunnersByGenderAndDistance(5);
        boolean ifGoesFromDownToTop = false;
        for (int i = 0; i < fiveRunners.size() - 1; i++) {
            if (fiveRunners.get(i + 1).getTime() > fiveRunners.get(i).getTime()) {
                ifGoesFromDownToTop = true;
            } else {
                ifGoesFromDownToTop = false;
            }
        }
        Assertions.assertTrue(ifGoesFromDownToTop);
    }

    /* ТЕСТЫ ДЛЯ СЕТТЕРОВ */
    // Тест проверяет, что будет, если указать пол, которого не существует, например 'К'
    @Test
    void testSetTargetGenderMethod_shouldThrowIllegalGenderException() throws IOException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> resultsProcessor.setTargetGender('К'));
    }

    // Тест проверяет, что будет, если указать дистанцию, которой нет в списке, например 3 км
    @Test
    void testSetTargetDistanceMethod_shouldThrowIllegalDistanceException() throws IOException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> resultsProcessor.setTargetDistance(3));
    }

    /* ТЕСТЫ ДЛЯ МЕТОДА GET FASTEST RUNNERS BY GENDER */
    // все тесты аналогичны
    @Test
    void testGetFastestRunnersByGenderMethod_shouldShowOnlyFemaleRunners() throws IOException {
        ArrayList<Runner> femaleRunners = resultsProcessor.getFastestRunnersByGender(5);
        boolean ifFemaleOnly = false;
        for (Runner runner : femaleRunners) {
            if (runner.getGender() == 'Ж') {
                ifFemaleOnly = true;
            } else {
                ifFemaleOnly = false;
            }
        }
        Assertions.assertTrue(ifFemaleOnly);
    }

    @Test
    void testGetFastestRunnersByGenderMethod_shouldShowThreeRunners() throws IOException {
        ArrayList<Runner> threeRunners = resultsProcessor.getFastestRunnersByGender(3);
        Assertions.assertEquals(3, threeRunners.size());
    }

    @Test
    void testGetFastestRunnersByGenderMethod_shouldShowSortedRunners() throws IOException {
        ArrayList<Runner> fiveRunners = resultsProcessor.getFastestRunnersByGender(5);
        boolean ifGoesFromDownToTop = false;
        for (int i = 0; i < fiveRunners.size() - 1; i++) {
            if (fiveRunners.get(i + 1).getTime() > fiveRunners.get(i).getTime()) {
                ifGoesFromDownToTop = true;
            } else {
                ifGoesFromDownToTop = false;
            }
        }
        Assertions.assertTrue(ifGoesFromDownToTop);
    }

    /* ТЕСТЫ ДЛЯ МЕТОДА GET FASTEST RUNNERS BY DISTANCE */
    // все тесты аналогичны
    @Test
    void testGetFastestRunnersByDistanceMethod_shouldShowThreeRunners() throws IOException {
        ArrayList<Runner> threeRunners = resultsProcessor.getFastestRunnersByDistance(3);
        Assertions.assertEquals(3, threeRunners.size());
    }

    @Test
    void testGetFastestRunnersByDistanceMethod_shouldShowSortedRunners() throws IOException {
        ArrayList<Runner> fiveRunners = resultsProcessor.getFastestRunnersByDistance(5);
        boolean ifGoesFromDownToTop = false;
        for (int i = 0; i < fiveRunners.size() - 1; i++) {
            if (fiveRunners.get(i + 1).getTime() > fiveRunners.get(i).getTime()) {
                ifGoesFromDownToTop = true;
            } else {
                ifGoesFromDownToTop = false;
            }
        }
        Assertions.assertTrue(ifGoesFromDownToTop);
    }

    @Test
    void testGetFastestRunnersByDistanceMethod_shouldShowOnlyTenKmDistanceRunners() throws IOException {
        ArrayList<Runner> tenKmRunners = resultsProcessor.getFastestRunnersByDistance(5);
        boolean ifTenKmOnly = false;
        for (Runner runner : tenKmRunners) {
            if (runner.getDistance() == 10) {
                ifTenKmOnly = true;
            } else {
                ifTenKmOnly = false;
            }
        }
        Assertions.assertTrue(ifTenKmOnly);
    }
}
