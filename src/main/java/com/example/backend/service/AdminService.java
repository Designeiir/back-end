package com.example.backend.service;

import com.example.backend.entity.Course;
import com.example.backend.entity.User;

import java.util.List;

public interface AdminService {
    int[] getCount();

    List<Course> getAllCourse(int page, int size);

    List<Course> getCourseBySearch(int page ,int size,String like);

    List<User> getAllUser(int page, int size);
}
