package Laba3;

import java.util.*;

public class Order {
    private List<String> items;               // Список товаров
    private String deliveryAddress;           // Адрес доставки
    private double totalCost;                 // Общая стоимость
    private String status;                    // Статус заказа

    public Order(List<String> items, String deliveryAddress, double totalCost) {
        this.items = new ArrayList<>(items);  // Копируем список товаров
        this.deliveryAddress = deliveryAddress; // Сохраняем адрес
        this.totalCost = totalCost;           // Сохраняем стоимость
        this.status = "CREATED";              // Устанавливаем начальный статус
    }

    public List<String> getItems() {          // Геттер списка товаров
        return new ArrayList<>(items);        // Возвращаем копию (защита от изменений)
    }

    public String getDeliveryAddress() {      // Геттер адреса
        return deliveryAddress;               // Возвращаем адрес
    }

    public double getTotalCost() {            // Геттер стоимости
        return totalCost;                     // Возвращаем стоимость
    }

    public String getStatus() {               // Геттер статуса
        return status;                        // Возвращаем статус
    }

    public void updateStatus(String newStatus) { // Обновить статус
        this.status = newStatus;              // Устанавливаем новый статус
    }

    @Override
    public boolean equals(Object o) {         // Переопределение equals
        if (this == o) return true;           // Один и тот же объект
        if (o == null || getClass() != o.getClass()) return false; // Проверка на null и тип
        Order order = (Order) o;              // Приведение к Order
        return Double.compare(order.totalCost, totalCost) == 0 && // Сравнение стоимости
                Objects.equals(items, order.items) &&              // Сравнение товаров
                Objects.equals(deliveryAddress, order.deliveryAddress) && // Адреса
                Objects.equals(status, order.status);              // Статусов
    }

    @Override
    public int hashCode() {                   // Переопределение hashCode
        return Objects.hash(items, deliveryAddress, totalCost, status); // Хеш по полям
    }

    @Override
    public String toString() {                // Строковое представление
        return "Order{" +
                "items=" + items +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", totalCost=" + totalCost +
                ", status='" + status + '\'' +
                '}';
    }
}



