package ru.itpark.sb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserOrderService {
    private final ObjectMapper mapper;

    private final String fileNameProducts = "src/main/resources/products.json";
    private String path = "src/main/resources/users.json";

    private List<Order> orders;
    private List<User> users;

    public UserOrderService() {
        this.mapper = JacksonConfiguration.initJackson();
    }

    public void readOrders() throws IOException {
        File path = new File(fileNameProducts);

        if (path.exists()){
            String json = new String(Files.readAllBytes(path.toPath()));
            orders =  mapper.readValue(json, new TypeReference<List<Order>>() {});
        }
        System.out.println("Файл не существует!");
    }

    public void readUsersFromFile() throws Exception{

        File file = new File(path);

        if (file.exists()){
            String json = new String(Files.readAllBytes(file.toPath()));
            users = mapper.readValue(json, new TypeReference<List<User>>() {});
        }
        System.out.println("Файл не существует!");
    }

    public Map<String, Long> getOrdersCount(){
        Map<Integer,User> userMap = users.stream()
                .collect(Collectors.toMap(u -> u.getId(), user -> user));

        Map<Integer, Long> orderCountByUserId =
                orders.stream()
                        .collect(Collectors.groupingBy(o -> o.getUserId(),Collectors.counting()));

        return orderCountByUserId.entrySet().stream()
                .collect(Collectors.toMap(
                        entry ->{
                            return userMap.get(entry.getKey()).getName();
                        },
                        e -> e.getValue()
                ));


    }
}
