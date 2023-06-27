package com.example.backend.service.impl;

import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.UserService;
import com.example.backend.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> login(User user) {
        Map<String, Object> map = new HashMap<>();
        // 判断用户名是否为空
        if(user.getName() == null || user.getName().equals("")) {
            map.put("code", 0);
            return map;
        }
        // 在数据库中寻找同名账户
        User selectUser = userMapper.selectOneByName(user.getName());
        if (selectUser == null) {
            map.put("code", -1);
            return map;
        }
        if (!selectUser.getPassword().equals(user.getPassword())) {
            map.put("code", 2);
            return map;
        }
        //将userId存入token中
        String token = JWTUtils.createToken(user.getName());
        map.put("code", 1);
        map.put("user", selectUser);
        map.put("token", token);

        return map;
    }

    @Override
    public List<User> selectAllUsers() {
        return userMapper.selectList(null);
    }
}
