package Laba6;

import java.util.LinkedHashMap;
import java.util.Map;

public class SalesTracker {
    LinkedHashMap<Product, Integer> sales;
    private double totalRevenue;

    public SalesTracker() {
        this.sales = new LinkedHashMap<>();
        this.totalRevenue = 0.0;
    }

    public void addProduct(Product product, int quantity) {
        if (product == null)
            throw new IllegalArgumentException("Продукт не может быть null");
        if (quantity <= 0)
            throw new IllegalArgumentException("Количество должно быть положительным");
        int currentCount = sales.getOrDefault(product, 0);
        sales.put(product, currentCount + quantity);
        totalRevenue += product.getPrice() * quantity;
    }

    public boolean isEmpty() {
        return sales.isEmpty();
    }

    public String getSales() {
        if (sales.isEmpty()) {
            return "Нет продаж";
        }

        StringBuilder result = new StringBuilder();
        result.append("Список проданных товаров:\n");

        int counter = 1;
        //Set <Map.Entry<Product, Integer>>
        for (Map.Entry<Product, Integer> entry : sales.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            result.append(counter++)
                    .append(". ")
                    .append(product.getName())
                    .append(" - ")
                    .append(quantity)
                    .append(" шт.\n");
        }

        return result.toString();
    }

    public int size() {
        return sales.size();
    }

    public int getProductSales(Product product) {
        return sales.getOrDefault(product, 0);
    }

    public Product getMostPopularProduct() {
        if (sales.isEmpty()) {
            return null;
        }

        Product mostPopular = null;
        int maxSales = 0;

        for (Map.Entry<Product, Integer> entry : sales.entrySet()) {
            if (entry.getValue() > maxSales) {
                maxSales = entry.getValue();
                mostPopular = entry.getKey();
            }
        }

        return mostPopular;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void clear() {
        sales.clear();
        totalRevenue = 0.0;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("=== ОТЧЕТ О ПРОДАЖАХ ===\n");

        if (isEmpty()) {
            info.append("Продаж нет\n");
        } else {
            info.append("Количество различных товаров: ").append(size()).append("\n");
            info.append("Общая сумма продаж: ").append(String.format("%.2f", totalRevenue)).append(" руб.\n");

            Product popular = getMostPopularProduct();
            if (popular != null) {
                info.append("Наиболее популярный товар: ")
                        .append(popular.getName())
                        .append(" (")
                        .append(getProductSales(popular))
                        .append(" шт.)\n");
            }

            info.append("\n").append(getSales());
        }

        return info.toString();
    }

    public static void main(String[] args) {
        SalesTracker tracker = new SalesTracker();

        Product bread = new Product("Хлеб", 50.0);
        Product milk = new Product("Молоко", 80.0);
        Product eggs = new Product("Яйца", 120.0);
        Product coffee = new Product("Кофе", 350.0);

        tracker.addProduct(bread, 3);
        tracker.addProduct(milk, 2);
        tracker.addProduct(eggs, 5);
        tracker.addProduct(coffee, 1);
        tracker.addProduct(bread, 2);

        System.out.println("=== ПРОГРАММА УЧЕТА ПРОДАЖ ===\n");
        System.out.println(tracker.toString());

        System.out.println("\nДополнительная информация:");
        System.out.println("Общая выручка: " + tracker.getTotalRevenue() + " руб.");

        Product popular = tracker.getMostPopularProduct();
        if (popular != null) {
            System.out.println("Самый популярный товар: " + popular.getName());
            System.out.println("Продано единиц: " + tracker.getProductSales(popular));
        }

        tracker.clear();
        System.out.println("\nПосле очистки:");
        System.out.println("Есть продажи? " + !tracker.isEmpty());
    }
}