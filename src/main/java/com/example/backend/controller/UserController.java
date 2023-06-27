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

    @PassToken
    @PostMapping("/login")
    public ResultInfo login(User user, HttpServletResponse response) {
        Map<String, Object> result = userService.login(user);
        int code = (int) result.get("code");
        if (code == 1) {
            response.setHeader(JWTUtils.USER_LOGIN_TOKEN, (String) result.get("token"));
            return ResultInfoUtils.success(result);
        }
        else if (code == 0) {
            return ResultInfoUtils.error(400, "用户名为空");
        }
        else if (code == -1){
            return ResultInfoUtils.error(400, "用户不存在");
        }
        else {
            return ResultInfoUtils.error(400, "密码错误");
        }
    }

    @GetMapping("/getAll")
    public ResultInfo getAllUsers() {
        List<User> users = userService.selectAllUsers();
        System.out.println(users);
        return ResultInfoUtils.success(users);
    }
}
