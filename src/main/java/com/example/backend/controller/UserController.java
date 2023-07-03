package com.example.backend.controller;

import com.example.backend.annotation.PassToken;
import com.example.backend.entity.ResultInfo;
import com.example.backend.entity.User;
import com.example.backend.exception.OSException;
import com.example.backend.exception.OSExceptionEnum;
import com.example.backend.service.UserService;
import com.example.backend.utils.JWTUtils;
import com.example.backend.utils.ResultInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    @PassToken
    public ResultInfo getAllUsers() {
        List<User> users = userService.selectAllUsers();
        System.out.println(users);
        return ResultInfoUtils.success(users);
    }

    @PostMapping("/updateUser")
    @PassToken
    public ResultInfo updateUser(@RequestBody User user) {
        int result = userService.updateUser(user);
        if (result != 0) {
            return ResultInfoUtils.success();
        }
        else {
            throw new OSException(OSExceptionEnum.DATABASE_ERROR);
        }
    }

    @PassToken
    @PostMapping("uploadImg")
    public ResultInfo uploadImg(HttpServletRequest request,
                                @RequestParam("file") MultipartFile img) {
        String url = userService.uploadImg(img);
        if (url == null || url.equals("")) {
            return ResultInfoUtils.error(500, "请求错误");
        }
        else {
            return ResultInfoUtils.success(url);
        }
    }


    @PassToken
    @GetMapping("/getTeacher")
    public ResultInfo getTeacher() {
        List<User> user = userService.selectAllTeachers();
        return ResultInfoUtils.success(user);
    }


}
