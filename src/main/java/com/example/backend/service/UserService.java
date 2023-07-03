package com.example.backend.service;

import com.example.backend.entity.Course;
import com.example.backend.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Map<String, Object> login(User user);
    public int register(Map<String, Object> params);
    public List<User> selectAllUsers();
    public List<User> selectAllTeachers();
}

