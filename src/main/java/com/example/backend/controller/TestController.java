package com.example.backend.controller;

import com.example.backend.annotation.PassToken;
import com.example.backend.entity.ResultInfo;
import com.example.backend.entity.User;
import com.example.backend.exception.OSException;
import com.example.backend.exception.OSExceptionEnum;
import com.example.backend.service.TestService;
import com.example.backend.utils.ResultInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {

    @Autowired
    private TestService testService;

    @PassToken
    @GetMapping("test")
    public String test() {
        return "成功！";
    }


    @GetMapping("testUser")
    public User testUser(int id) {
        return testService.testUser(id);
    }

    @GetMapping("/testToken")
    public ResultInfo testToken() {
        return ResultInfoUtils.success();
    }

    @PassToken
    @GetMapping("/testException")
    public ResultInfo testException() {
        throw new OSException("测试抛出异常");
    }

    @PassToken
    @GetMapping("/testGlobal")
    public ResultInfo testGlobalException() {
        throw new OSException(OSExceptionEnum.EMPTY_DATA);
    }


}
