package Laba3;

import java.util.*;

public class HashTable<K, V> {
    private LinkedList<Entry<K, V>>[] table;  // Массив корзин (списков)
    private int size;                         // Количество элементов
    private static final int INITIAL_CAPACITY = 16; // Начальный размер

    /*
    Аннотация @SuppressWarnings("unchecked") в данном случае
    используется для подавления предупреждения компилятора о небезопасном приведении типов (unchecked cast),
    которое возникает при создании массива обобщённого (generic) типа
     */

    @SuppressWarnings("unchecked")
    public HashTable() {                      // Конструктор
        table = new LinkedList[INITIAL_CAPACITY]; // Создаём массив
        size = 0;                             // Изначально пусто
    }

    private int hash(K key) {                 // Хэш-функция
        if (key == null) return 0;            // null → индекс 0
        return Math.abs(key.hashCode()) % table.length; // Индекс в пределах
    }

    public void put(K key, V value) {         // Добавить/обновить
        int index = hash(key);                // Вычисляем индекс
        if (table[index] == null) {           // Если корзина пуста
            table[index] = new LinkedList<>(); // Создаём список
        }
        for (Entry<K, V> entry : table[index]) { // Ищем ключ
            if (Objects.equals(entry.getKey(), key)) { // Найден
                entry.setValue(value);        // Обновляем значение
                return;                       // Выходим
            }
        }
        table[index].add(new Entry<>(key, value)); // Добавляем новую запись
        size++;                               // Увеличиваем счётчик
    }

    public V get(K key) {                     // Получить значение
        int index = hash(key);                // Индекс по ключу
        if (table[index] == null) return null; // Корзина пуста
        for (Entry<K, V> entry : table[index]) { // Ищем в списке
            if (Objects.equals(entry.getKey(), key)) { // Ключ найден
                return entry.getValue();      // Возвращаем значение
            }
        }
        return null;                          // Не найден
    }

    public V remove(K key) {                  // Удалить по ключу
        int index = hash(key);                // Индекс
        if (table[index] == null) return null; // Нет корзины
        Iterator<Entry<K, V>> iterator = table[index].iterator(); // Итератор
        while (iterator.hasNext()) {          // Обход списка
            Entry<K, V> entry = iterator.next(); // Следующий элемент
            if (Objects.equals(entry.getKey(), key)) { // Нашли ключ
                V oldValue = entry.getValue(); // Сохраняем значение
                iterator.remove();            // Удаляем из списка
                size--;                       // Уменьшаем счётчик
                return oldValue;              // Возвращаем значение
            }
        }
        return null;                          // Ключ не найден
    }

    public int size() {                       // Размер таблицы
        return size;                          // Возвращаем счётчик
    }

    public boolean isEmpty() {                // Пуста ли таблица?
        return size == 0;                     // true, если нет элементов
    }

    public List<V> values() {                 // Все значения таблицы
        List<V> allValues = new ArrayList<>(); // Список для результата
        for (LinkedList<Entry<K, V>> bucket : table) { // Обход корзин
            if (bucket != null) {             // Если корзина не null
                for (Entry<K, V> entry : bucket) { // Обход записей
                    allValues.add(entry.getValue()); // Добавляем значение
                }
            }
        }
        return allValues;                     // Возвращаем все значения
    }
}