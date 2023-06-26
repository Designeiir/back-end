package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("test")
    public String test() {
        return "成功！";
    }

    @GetMapping("testUser")
    public User testUser(int id) {
        return testService.testUser(id);
    }
}
