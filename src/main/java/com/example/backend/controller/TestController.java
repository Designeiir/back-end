package com.example.backend.controller;

import com.example.backend.annotation.PassToken;
import com.example.backend.entity.ResultInfo;
import com.example.backend.entity.User;
import com.example.backend.service.TestService;
import com.example.backend.utils.ResultInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @PassToken
    @GetMapping("test")
    public String test() {
        return "成功！";
    }

    @PassToken
    @GetMapping("testUser")
    public User testUser(int id) {
        return testService.testUser(id);
    }

    @GetMapping("/testToken")
    public ResultInfo testToken() {
        return ResultInfoUtils.success();
    }
}
