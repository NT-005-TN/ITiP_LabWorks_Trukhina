package Laba7;
import java.util.Random;

public class ArraySumTask {
    private int[] array;
    private long sumFirstHalf = 0;
    private long sumSecondHalf = 0;

    public static void main(String[] args) throws InterruptedException {
        ArraySumTask task = new ArraySumTask();
        task.calculateSum();
    }

    public void calculateSum() throws InterruptedException {
        // Создаем и заполняем массив
        array = new int[100];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }

        // Создаем два потока
        Thread thread1 = new Thread(new FirstHalfCalculator());
        Thread thread2 = new Thread(new SecondHalfCalculator());

        // Запускаем потоки
        thread1.start();
        thread2.start();

        // Ждем завершения потоков
        thread1.join();
        thread2.join();

        // Суммируем результаты в главном потоке
        long totalSum = sumFirstHalf + sumSecondHalf;

        // Проверяем результат
        long sequentialSum = 0;
        for (int value : array) {
            sequentialSum += value;
        }

        System.out.println("Сумма первой половины: " + sumFirstHalf);
        System.out.println("Сумма второй половины: " + sumSecondHalf);
        System.out.println("Общая сумма (многопоточная): " + totalSum);
        System.out.println("Общая сумма (последовательная): " + sequentialSum);
        System.out.println("Результаты совпадают: " + (totalSum == sequentialSum));
    }

    // Класс для вычисления суммы первой половины
    class FirstHalfCalculator implements Runnable {
        @Override
        public void run() {
            int middle = array.length / 2;
            for (int i = 0; i < middle; i++) {
                sumFirstHalf += array[i];
            }
            System.out.println("Поток 1 завершил вычисление первой половины");
        }
    }

    // Класс для вычисления суммы второй половины
    class SecondHalfCalculator implements Runnable {
        @Override
        public void run() {
            int middle = array.length / 2;
            for (int i = middle; i < array.length; i++) {
                sumSecondHalf += array[i];
            }
            System.out.println("Поток 2 завершил вычисление второй половины");
        }
    }
}