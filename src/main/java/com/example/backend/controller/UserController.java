package com.example.backend.controller;

import com.example.backend.annotation.PassToken;
import com.example.backend.entity.ResultInfo;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import com.example.backend.utils.JWTUtils;
import com.example.backend.utils.ResultInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * */
    @PassToken
    @PostMapping("/login")
    public ResultInfo login(@RequestBody User user, HttpServletResponse response) {
        Map<String, Object> result = userService.login(user);
        response.setHeader(JWTUtils.USER_LOGIN_TOKEN, (String) result.get("token"));
        return ResultInfoUtils.success(result);
    }

    @PassToken
    @PostMapping("/register")
    public ResultInfo register(@RequestBody Map<String, Object> params) {
        int result = userService.register(params);
        System.out.println(result);
        return ResultInfoUtils.success();
    }

    @GetMapping("/getAll")
    public ResultInfo getAllUsers() {
        List<User> users = userService.selectAllUsers();
        System.out.println(users);
        return ResultInfoUtils.success(users);
    }
}
