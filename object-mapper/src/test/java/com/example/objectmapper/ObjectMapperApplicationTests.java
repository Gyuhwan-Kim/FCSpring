package com.example.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectMapperApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("-----------");

        var objectMapper = new ObjectMapper();

        // Object -> Text
        var user = new User("lueble", 29, "010-1234-5678");
        var text = objectMapper.writeValueAsString(user);
        System.out.println(text);

        // Text -> Object
        var objectUser = objectMapper.readValue(text, User.class);
        System.out.println(objectUser);
    }
}
