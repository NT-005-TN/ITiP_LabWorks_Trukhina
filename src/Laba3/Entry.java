package Laba3;

public class Entry<K, V> {
    K key;                          // Ключ пары
    V value;                        // Значение пары

    public Entry(K key, V value) {  // Конструктор записи
        this.key = key;             // Сохраняем ключ
        this.value = value;         // Сохраняем значение
    }

    public K getKey() {             // Геттер для ключа
        return key;                 // Возвращаем ключ
    }

    public V getValue() {           // Геттер для значения
        return value;               // Возвращаем значение
    }

    public void setValue(V value) { // Сеттер для значения
        this.value = value;         // Обновляем значение
    }
}