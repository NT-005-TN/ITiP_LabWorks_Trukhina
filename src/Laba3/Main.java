package Laba3;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        HashTable<String, Integer> myTable = new HashTable<>();

        myTable.put("one", 1);
        myTable.put("two", 2);
        myTable.put("three", 3);

        System.out.println("Size: " + myTable.size());
        System.out.println("Get 'two': " + myTable.get("two"));
        System.out.println("Removed 'two': " + myTable.remove("two"));
        System.out.println("Size after remove: " + myTable.size());
        System.out.println("Is empty: " + myTable.isEmpty());



        HashTable<String, Order> orders = new HashTable<>();

        Order order1 = new Order("ORD001", "Москва");
        order1.addItem(new Item("Книга", 500.0));

        Order order2 = new Order("ORD002", "СПб");
        order2.addItem(new Item("Мышь", 300.0));

        orders.put(order1.orderNumber, order1);
        orders.put(order2.orderNumber, order2);

        System.out.println("Найден заказ: " + orders.get("ORD001"));

        order1.updateStatus("Отправлен");
        System.out.println("После изменения статуса: " + orders.get("ORD001"));

        orders.remove("ORD002");
        System.out.println("Размер после удаления: " + orders.size());
    }
}

