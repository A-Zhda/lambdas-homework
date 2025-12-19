package ru.itpark.sb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderService {

    private final ObjectMapper mapper;

    private final String fileName = "src/main/resources/products.json";

    public OrderService() {
        this.mapper = JacksonConfiguration.initJackson();
    }

    public List<Order> readOrders() throws IOException {
        File path = new File(fileName);

        if (path.exists()){
            String json = new String(Files.readAllBytes(path.toPath()));
            return mapper.readValue(json, new TypeReference<List<Order>>() {});
        }
        System.out.println("Файл не существует!");
        return List.of();
    }

    public double calculateSumOfOrders(List<Order> orders){
        return orders.stream().mapToDouble(o -> o.getSum()).sum();
    }

    public Optional<String> findTopProduct(List<Order> orders){
        return orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(
                        p -> p.getProduct(),
                       Collectors.summingDouble(p-> p.getPrice() * p.getQty())
                ))
                .entrySet().stream()
                .max((e1,e2) -> Double.compare(e1.getValue(),e2.getValue()))
                .map(entry -> entry.getKey() + ", "+entry.getValue());

    }


}
