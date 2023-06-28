package com.example.backend.service.impl;

import com.example.backend.entity.User;
import com.example.backend.exception.OSException;
import com.example.backend.exception.OSExceptionEnum;
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

    /**
     * 登录业务
     * */
    @Override
    public Map<String, Object> login(User user) throws OSException {
        Map<String, Object> map = new HashMap<>();
        // 判断用户名是否为空
        if(user.getName() == null || user.getName().equals("")) {
            throw new OSException(OSExceptionEnum.USER_PARAM_NULL);
        }
        // 在数据库中寻找同名账户
        User selectUser = userMapper.selectOneByName(user.getName());
        if (selectUser == null) {
            throw new OSException(OSExceptionEnum.USER_EMPTY);
        }
        // 判断密码是否错误
        if (!selectUser.getPassword().equals(user.getPassword())) {
            throw new OSException(OSExceptionEnum.PASSWORD_ERROR);
        }
        //将userId存入token中
        String token = JWTUtils.createToken(user.getName());
        map.put("user", selectUser);
        map.put("token", token);

        return map;
    }

    @Override
    public List<User> selectAllUsers() {
        return userMapper.selectList(null);
    }
}
