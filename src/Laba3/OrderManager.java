package Laba3;

import java.util.*;

public class OrderManager {
    private HashTable<Long, Order> orders = new HashTable<>(); // Собственная хэш-таблица

    public void addOrder(Long orderId, Order order) { // Добавить заказ
        orders.put(orderId, order);                   // Используем put из HashTable
    }

    public Order findOrder(Long orderId) {            // Найти заказ
        return orders.get(orderId);                   // Используем get из HashTable
    }

    public Order removeOrder(Long orderId) {          // Удалить заказ
        return orders.remove(orderId);                // Используем remove из HashTable
    }

    public boolean updateOrderStatus(Long orderId, String newStatus) { // Изменить статус
        Order order = orders.get(orderId);            // Получаем заказ
        if (order != null) {                          // Если существует
            order.updateStatus(newStatus);            // Меняем статус
            return true;                              // Успешно
        }
        return false;                                 // Заказ не найден
    }

    public List<Order> getAllOrders() {               // Все заказы
        return orders.values();                       // Используем values() из HashTable
    }
}