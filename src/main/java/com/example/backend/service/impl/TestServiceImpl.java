package com.example.backend.service.impl;

import com.example.backend.entity.User;
import com.example.backend.mapper.TestMapper;
import com.example.backend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;


    @Override
    public User testUser(int id) {
        User user = testMapper.selectById(id);
        return user;
    }
}
