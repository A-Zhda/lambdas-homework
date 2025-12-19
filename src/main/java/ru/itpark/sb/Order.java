package ru.itpark.sb;

import java.util.List;

public class Order {
    private int id;
    private int userId;
    private double sum;
    private List<item> items;

    public Order(int id, int userId, double sum, List<item> items) {
        this.id = id;
        this.userId = userId;
        this.sum = sum;
        this.items = items;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public double getSum() {
        return sum;
    }

    public List<item> getItems() {
        return items;
    }
}
