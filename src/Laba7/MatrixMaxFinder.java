package Laba7;
import java.util.Random;

public class MatrixMaxFinder {
    private int[][] matrix;
    private int maxInRow[];
    private int globalMax = Integer.MIN_VALUE;

    public static void main(String[] args) throws InterruptedException {
        MatrixMaxFinder finder = new MatrixMaxFinder();
        finder.findMaxElement();
    }

    public void findMaxElement() throws InterruptedException {
        int rows = 5;
        int cols = 5;
        matrix = new int[rows][cols];
        maxInRow = new int[rows];

        Random random = new Random();

        System.out.println("Матрица:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(1000);
                System.out.printf("%4d ", matrix[i][j]);
            }
            System.out.println();
        }

        Thread[] threads = new Thread[rows];

        for (int i = 0; i < rows; i++) {
            int rowIndex = i;
            threads[i] = new Thread(new RowProcessor(rowIndex));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        for (int i = 0; i < rows; i++) {
            if (maxInRow[i] > globalMax) {
                globalMax = maxInRow[i];
            }
        }


        System.out.println("\nМаксимальные элементы в строках:");
        for (int i = 0; i < rows; i++) {
            System.out.println("Строка " + i + ": " + maxInRow[i]);
        }
        System.out.println("\nГлобальный максимум (многопоточный): " + globalMax);
    }

    class RowProcessor implements Runnable {
        private int rowIndex;

        public RowProcessor(int rowIndex) {
            this.rowIndex = rowIndex;
        }

        @Override
        public void run() {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < matrix[rowIndex].length; j++) {
                if (matrix[rowIndex][j] > max) {
                    max = matrix[rowIndex][j];
                }
            }
            maxInRow[rowIndex] = max;
            System.out.println("Поток для строки " + rowIndex + " нашел максимум: " + max);
        }
    }
}

























/*
или другой подзод:
public void findMaxElement() throws InterruptedException {
    // ...
    ExecutorService executor = Executors.newFixedThreadPool(rows);

    for (int i = 0; i < rows; i++) {
        final int rowIndex = i;
        executor.submit(new RowProcessor(rowIndex));
    }

    executor.shutdown();  // Завершаем прием задач
    executor.awaitTermination(1, TimeUnit.MINUTES);  // Ждем завершения
    // ...
}



 */