package Laba3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("ДЕМОНСТРАЦИЯ РАБОТЫ HashTable");
        System.out.println("=".repeat(60));

        HashTable<String, Integer> ht = new HashTable<>();

        // isEmpty()
        System.out.println("1. isEmpty() → " + ht.isEmpty()); // true

        // put()
        ht.put("apple", 5);
        ht.put("banana", 3);
        ht.put("orange", 7);
        System.out.println("2. Добавлены: apple " + ht.get("apple") + ", banana " + ht.get("banana") + ", orange " + ht.get("orange"));

        // size()
        System.out.println("3. size() → " + ht.size()); // 3

        // get()
        System.out.println("4. get('apple') → " + ht.get("apple"));     // 5
        System.out.println("   get('grape') → " + ht.get("grape"));     // null

        // put() с обновлением
        ht.put("apple", 10);
        System.out.println("5. Обновили 'apple' на 10 → get('apple') = " + ht.get("apple"));

        // remove()
        int removed = ht.remove("banana");
        System.out.println("6. remove('banana') → " + removed);         // 3
        System.out.println("   get('banana') после удаления → " + ht.get("banana")); // null
        System.out.println("   size() после удаления → " + ht.size());  // 2

        // isEmpty() после добавления
        System.out.println("7. isEmpty() после операций → " + ht.isEmpty()); // false

        System.out.println("\n" + "=".repeat(60));
        System.out.println("ДЕМОНСТРАЦИЯ РАБОТЫ Order и OrderManager (Вариант 7)");
        System.out.println("=".repeat(60));

        OrderManager manager = new OrderManager();

        // Создаём заказы
        List<String> items1 = Arrays.asList("Ноутбук", "Мышь");
        Order order1 = new Order(items1, "Москва, Тверская 1", 95000.0);
        Order order2 = new Order(
                Arrays.asList("Книга"),
                "СПб, Невский 10",
                1200.0
        );

        // toString()
        System.out.println("1. order1.toString() → " + order1);

        // Геттеры
        System.out.println("2. order1.getItems() → " + order1.getItems());
        System.out.println("   order1.getDeliveryAddress() → " + order1.getDeliveryAddress());
        System.out.println("   order1.getTotalCost() → " + order1.getTotalCost());
        System.out.println("   order1.getStatus() → " + order1.getStatus());

        // updateStatus()
        order1.updateStatus("SHIPPED");
        System.out.println("3. После updateStatus('SHIPPED') → статус: " + order1.getStatus());

        // equals() и hashCode()
        Order order1Copy = new Order(items1, "Москва, Тверская 1", 95000.0);
        order1Copy.updateStatus("SHIPPED"); // чтобы статус совпадал
        System.out.println("4. order1.equals(order1Copy) → " + order1.equals(order1Copy)); // true
        System.out.println("   order1.hashCode() == order1Copy.hashCode() → " + (order1.hashCode() == order1Copy.hashCode())); // true

        // addOrder()
        manager.addOrder(1001L, order1);
        manager.addOrder(1002L, order2);
        System.out.println("5. Заказы 1001 и 1002 добавлены в OrderManager");

        // findOrder()
        System.out.println("6. findOrder(1001) → " + manager.findOrder(1001L));
        System.out.println("   findOrder(999) → " + manager.findOrder(999L)); // null

        // updateOrderStatus()
        boolean updated = manager.updateOrderStatus(1002L, "PROCESSING");
        System.out.println("7. updateOrderStatus(1002, 'PROCESSING') → " + updated); // true
        System.out.println("   Статус заказа 1002 теперь: " + manager.findOrder(1002L).getStatus());

        // getAllOrders()
        System.out.println("8. Всего заказов: " + manager.getAllOrders().size()); // 2

        // removeOrder()
        Order removedOrder = manager.removeOrder(1001L);
        System.out.println("9. removeOrder(1001) → удалён: " + (removedOrder != null));
        System.out.println("   findOrder(1001) после удаления → " + manager.findOrder(1001L)); // null
        System.out.println("   Всего заказов после удаления: " + manager.getAllOrders().size()); // 1

        // Попытка обновить несуществующий заказ
        boolean failedUpdate = manager.updateOrderStatus(9999L, "DELIVERED");
        System.out.println("10. updateOrderStatus(9999, 'DELIVERED') → " + failedUpdate); // false
    }
}