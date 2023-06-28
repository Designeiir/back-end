package com.example.backend.service.impl;

import com.example.backend.entity.InviteCode;
import com.example.backend.entity.User;
import com.example.backend.exception.OSException;
import com.example.backend.exception.OSExceptionEnum;
import com.example.backend.mapper.InviteCodeMapper;
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
    @Autowired
    private InviteCodeMapper inviteCodeMapper;

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

    /**
     * 用户注册
     * */
    @Override
    public int register(Map<String, Object> params) {
        // 获得传递参数
        User user = new User();
        user.setName((String) params.get("name"));
        user.setPassword((String) params.get("password"));
        user.setAge((int) params.get("age"));
        user.setPhoneNumber((String) params.get("phoneNumber"));
        user.setSex((int) params.get("sex"));
        user.setType((int) params.get("type"));

        String inviteCode = (String) params.get("invite_code");

        //判断参数是否满足条件
        if (user.getName() == null || user.getName().equals("")) {
            throw new OSException(OSExceptionEnum.USER_PARAM_NULL);
        }
        if (userMapper.selectOneByName(user.getName()) != null) {
            throw new OSException(OSExceptionEnum.USER_EXISTS);
        }
        if (user.getAge() <= 0 || user.getSex() < 0 || user.getSex() > 1 || user.getType() < 0 || user.getType() > 2) {
            throw new OSException(OSExceptionEnum.PARAM_ERROR);
        }
        if (user.getPhoneNumber() == null || user.getPhoneNumber().equals("")) {
            throw new OSException(OSExceptionEnum.PARAM_ERROR);
        }
        // 判断邀请码合法性
        if (user.getType() == 1 && !validInviteCode(inviteCode)) {
            throw new OSException(OSExceptionEnum.INVITE_CODE_ERROR);
        }
        // 判断密码合法性
        if (!validPassword(user.getPassword())) {
            throw new OSException(OSExceptionEnum.PASSWORD_ERROR);
        }
        // 持久化数据
        int result = userMapper.insert(user);

        return result;
    }

    /**
     * 验证邀请码合法性
     *  合法为true，不合法为false
     * */
    public boolean validInviteCode(String inviteCode) {
        InviteCode code = inviteCodeMapper.selectById(inviteCode);
        return code != null;
    }

    /**
     * 验证密码合法性
     *  合法为true，不合法为false
     * */
    public boolean validPassword(String password) {
        // TODO: 判断密码的合法性
        return true;
    }

    @Override
    public List<User> selectAllUsers() {
        return userMapper.selectList(null);
    }
}
