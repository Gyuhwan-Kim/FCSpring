package com.example.response.controller;

import com.example.response.dto.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }

    // ResponseEntity

    @ResponseBody
    @GetMapping("/user")
    public User user(){
        var user = new User(); // Type 추론 Java 11
        user.setName("lueble");
        user.setAddress("Seoul");
        return user;
    }


}
