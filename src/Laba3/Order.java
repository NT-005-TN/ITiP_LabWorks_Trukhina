package Laba3;

import java.util.ArrayList;
import java.util.List;

class Order {
    String orderNumber;
    List<Item> items;
    String deliveryAddress;
    double totalCost;
    String status;

    Order(String orderNumber, String deliveryAddress) {
        this.orderNumber = orderNumber;
        this.deliveryAddress = deliveryAddress;
        this.items = new ArrayList<>();
        this.totalCost = 0.0;
        this.status = "Новый";
    }

    void addItem(Item item) {
        items.add(item);
        totalCost += item.price;
    }

    void updateStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" + orderNumber + ", " + items + ", " + deliveryAddress + ", " + totalCost + ", " + status + "}";
    }
}



