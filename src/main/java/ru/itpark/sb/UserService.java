package ru.itpark.sb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserService {
    private final ObjectMapper mapper;
    private String path = "src/main/resources/users.json";

    public UserService() {
        this.mapper = JacksonConfiguration.initJackson();
    }

    public List<User> readUsersFromFile() throws Exception{

        File file = new File(path);

        if (file.exists()){
            String json = new String(Files.readAllBytes(file.toPath()));
            return mapper.readValue(json, new TypeReference<List<User>>() {});
        }
        System.out.println("Файл не существует!");
        return List.of();
    }

    public Map<String,Double> averageAgeByCity(List<User> users){
        return users.stream().collect(
                Collectors.groupingBy(user -> user.getCity(),
                        Collectors.averagingInt(user -> user.getAge())));
    }

    public String usersYoJson(List<User> users) throws JsonProcessingException {
        return mapper.writeValueAsString(users);
    }
}
