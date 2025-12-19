package ru.itpark.sb;

import java.io.IOException;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException {

        OrderService orderService = new OrderService();

        List<Order> orders = orderService.readOrders();

        System.out.println(orderService.calculateSumOfOrders(orders));
        System.out.println(orderService.findTopProduct(orders).orElse("-"));

    }
}
