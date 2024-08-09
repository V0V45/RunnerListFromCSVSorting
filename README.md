# Сортировщик результатов соревнований
## Как пользоваться
1. Открывается ApplicationContext.java
2. В строчке
```java
ResultsProcessor processor = new ResultsProcessor("base.csv")
```
вместо
```java
"base.csv"
```
задается путь к csv файлу. Структура файла следующая: `[фамилия];[имя];[пол(М или Ж)];[дистанция(5 км или 10 км)];[время ММ:СС]`
Пример csv-файла лежит в корне проекта (base.csv).
3. С помощью сеттеров задаются фильтры, по которым будет сортироваться список. Задание хотя бы одного из сеттеров обязательно.
В строчках
```java
processor.setTargetDistance(10);
processor.setTargetGender('Ж');
```
вместо 10 задается требуемая дистанция для сортировки (5 или 10), и вместо 'Ж' задается пол ('М' или 'Ж').
4. Открывается CreatorApplication.java
5. В строчках
```java
ArrayList<Runner> testList = processor.getFastestRunnersByGenderAndDistance(5);
processor.printRunners(testList);
```
создается список, который сортирует список бегунов от самого быстрого к самому медленному сразу по полу и дистанции.
В качестве аргумента задается, сколько человек из списка будет выведено (в данном случае - `5`).
Затем, с помощью метода `printRunners` данный список выводится в консоль в красивом виде.
Вместо метода `getFastestRunnersByGenderAndDistance` можно использовать другие сортировки:
```java
processor.getFastestRunnersByDistance(5) // сортирует только по дистанции
processor.getFastestRunnersByGender(5) // сортирует только по полу
```
## Файловая структура проекта
- base.csv - пример списка бегунов
- base.xlsx - тот же самый пример списка бегунов, только в формате Excel
### src/main/java/com/filecreator/creator
- ApplicationContext.java - конфигурационный файл Spring в явном виде
- CreatorApplication.java - Main-файл, содержащий запуск программы
- ResultsProcessor.java - основной класс, который преобразует поступающий CSV-файл в ArrayList
- Runner.java - класс, представляющий собой модель бегуна
### src/test
- ResultsProcessorTest.java - Unit-тесты методов класса ResultsProcessor

