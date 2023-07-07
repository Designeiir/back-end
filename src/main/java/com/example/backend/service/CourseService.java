package com.example.backend.service;

import com.example.backend.entity.Course;
import com.example.backend.entity.User;

import java.util.List;
import java.util.Map;

public interface CourseService {
    public Map<String, Object> selectCourse(int cid);
    public List<Map<String, Object>> selectCourseRand(int num);
    public List<Map<String, Object>> selectCourseRecommend(String key);
}