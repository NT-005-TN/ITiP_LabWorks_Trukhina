package Laba7;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Warehouse {
    // Объявление констант программы
    private static final int WORKER_COUNT = 3;  // Количество рабочих потоков
    private static final int MAX_WEIGHT = 150;  // Максимальная грузоподъемность

    // Объявление разделяемых данных между потоками
    private int[] items;              // Массив весов товаров
    private boolean[] itemsMoved;     // Флаги перемещения товаров
    private int currentWeight = 0;    // Текущий вес в грузовике (разделяемая переменная)
    private int deliveredTrips = 0;   // Счетчик рейсов (разделяемая переменная)

    // Механизмы синхронизации
    private ReentrantLock lock = new ReentrantLock();          // Блокировка для критических секций
    private Condition truckCondition = lock.newCondition();    // Условная переменная для ожидания грузовика

    // Флаг состояния системы (разделяемая переменная)
    private boolean truckInTransit = false;  // true = грузовик в пути

    // Точка входа в программу
    public static void main(String[] args) throws InterruptedException {
        // Создание объекта склада
        Warehouse warehouse = new Warehouse();
        // Запуск основной логики
        warehouse.startWork();
    }

    // Основной метод работы склада
    public void startWork() throws InterruptedException {
        // Инициализация генератора случайных чисел
        Random random = new Random();
        // Количество товаров на складе
        int itemCount = 30;
        // Создание массива товаров
        items = new int[itemCount];
        // Создание массива флагов перемещения
        itemsMoved = new boolean[itemCount];

        // Вывод информации о товарах
        System.out.println("На складе " + itemCount + " товаров:");
        // Инициализация товаров случайными весами
        for (int i = 0; i < itemCount; i++) {
            // Генерация веса товара (1-50 кг)
            items[i] = random.nextInt(50) + 1;
            // Инициализация флага "не перемещен"
            itemsMoved[i] = false;
            // Вывод информации о товаре
            System.out.println("Товар " + (i + 1) + ": " + items[i] + " кг");
        }
        System.out.println("===========================================\n");

        // Создание массива рабочих потоков
        Thread[] workers = new Thread[WORKER_COUNT];
        // Создание и запуск потоков-грузчиков
        for (int i = 0; i < WORKER_COUNT; i++) {
            // Создание потока с задачей Worker
            workers[i] = new Thread(new Worker(i + 1));
            // Запуск потока (начинает выполнять run())
            workers[i].start();
        }

        // Ожидание завершения всех рабочих потоков (синхронизация)
        for (Thread worker : workers) {
            // Блокировка главного потока до завершения рабочего
            worker.join();
        }

        // Отправка последнего грузовика если в нем есть груз
        if (currentWeight > 0) {
            sendTruck();
        }

        // Вывод итогов работы
        System.out.println("\n=== РАБОТА ЗАВЕРШЕНА ===");
        System.out.println("Количество рейсов: " + deliveredTrips);

        // Проверка перемещения всех товаров
        boolean allMoved = true;
        // Проверка всех флагов перемещения
        for (boolean moved : itemsMoved) {
            // Если найден неперемещенный товар
            if (!moved) {
                allMoved = false;
                // Прерывание проверки
                break;
            }
        }
        System.out.println("Все товары перемещены: " + allMoved);
    }

    // Класс-задача для рабочего потока
    class Worker implements Runnable {
        // Идентификатор грузчика
        private int workerId;

        // Конструктор грузчика
        public Worker(int workerId) {
            // Инициализация идентификатора
            this.workerId = workerId;
        }

        // Метод, выполняемый в отдельном потоке
        @Override
        public void run() {
            // Сообщение о начале работы
            System.out.println("Грузчик " + workerId + " начал работу");

            // Бесконечный цикл обработки товаров
            while (true) {
                // Захват блокировки перед доступом к общим данным
                lock.lock();
                try {
                    // Ожидание пока грузовик не вернется
                    while (truckInTransit) {
                        System.out.println("Грузчик " + workerId + " ждет, пока грузовик вернется...");
                        // Освобождение lock и ожидание сигнала
                        truckCondition.await();
                    }

                    // Поиск неперемещенного товара
                    int itemIndex = -1;
                    // Линейный поиск по массиву
                    for (int i = 0; i < items.length; i++) {
                        // Проверка флага перемещения
                        if (!itemsMoved[i]) {
                            // Сохранение индекса найденного товара
                            itemIndex = i;
                            // Прерывание поиска
                            break;
                        }
                    }

                    // Проверка наличия товаров
                    if (itemIndex == -1) {
                        System.out.println("Грузчик " + workerId + ": все товары обработаны");
                        // Выход из бесконечного цикла
                        break;
                    }

                    // Помечаем товар как перемещенный
                    itemsMoved[itemIndex] = true;
                    // Получаем вес товара
                    int itemWeight = items[itemIndex];

                    System.out.println("Грузчик " + workerId + " взял товар " +
                            (itemIndex + 1) + " весом " + itemWeight + " кг");

                    // Проверка на переполнение грузовика
                    if (currentWeight + itemWeight > MAX_WEIGHT) {
                        System.out.println("Грузчик " + workerId + ": грузовик переполнится, нужно отправить!");
                        // Отправка существующего груза
                        sendTruck();
                    }

                    // Добавление веса товара к текущему весу
                    currentWeight += itemWeight;
                    System.out.println("Текущий вес в грузовике: " + currentWeight + " кг");

                    // Проверка заполнения грузовика
                    if (currentWeight >= MAX_WEIGHT) {
                        System.out.println("Грузчик " + workerId + ": грузовик полон!");
                        // Отправка грузовика
                        sendTruck();
                    }

                } catch (InterruptedException e) {
                    // Восстановление флага прерывания
                    Thread.currentThread().interrupt();
                    System.out.println("Грузчик " + workerId + " был прерван");
                    // Выход из цикла при прерывании
                    break;
                } finally {
                    // Гарантированное освобождение блокировки
                    lock.unlock();
                }

                // Имитация физической работы грузчика
                try {
                    // Случайная задержка 100-300 мс
                    Thread.sleep(100 + new Random().nextInt(200));
                } catch (InterruptedException e) {
                    // Восстановление флага прерывания
                    Thread.currentThread().interrupt();
                }
            }

            // Сообщение о завершении работы
            System.out.println("Грузчик " + workerId + " завершил работу");
        }
    }

    // Метод отправки грузовика
    private void sendTruck() {
        // Захват блокировки
        lock.lock();
        try {
            // Проверка наличия груза
            if (currentWeight == 0) {
                // Выход если грузовик пустой
                return;
            }

            // Установка флага "грузовик в пути"
            truckInTransit = true;

            System.out.println("\n--- ГРУЗОВИК ОТПРАВЛЯЕТСЯ ---");
            System.out.println("Вес груза: " + currentWeight + " кг");

            // Освобождение блокировки перед долгой операцией
            lock.unlock();

            // Имитация времени доставки
            try {
                // Случайная задержка 500-1500 мс
                Thread.sleep(500 + new Random().nextInt(1000));
            } catch (InterruptedException e) {
                // Восстановление флага прерывания
                Thread.currentThread().interrupt();
            }

            // Повторный захват блокировки для обновления состояния
            lock.lock();

            // Увеличение счетчика рейсов
            deliveredTrips++;
            System.out.println("Грузовик разгружен. Рейсов выполнено: " + deliveredTrips);
            System.out.println("--- ГРУЗОВИК ВЕРНУЛСЯ ---\n");

            // Обнуление веса в грузовике
            currentWeight = 0;

            // Сброс флага "грузовик в пути"
            truckInTransit = false;
            // Пробуждение всех ожидающих грузчиков
            truckCondition.signalAll();

        } catch (Exception e) {
            // Обработка исключений
            e.printStackTrace();
        } finally {
            // Проверка владения блокировкой перед освобождением
            if (lock.isHeldByCurrentThread()) {
                // Освобождение блокировки если владеем ею
                lock.unlock();
            }
        }
    }
}