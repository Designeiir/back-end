package com.example.backend.service;

import com.example.backend.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Map<String, Object> login(User user);
    public int register(Map<String, Object> params);
    public List<User> selectAllUsers();
    int updateUser(User user);
    String uploadImg(MultipartFile img);
}
