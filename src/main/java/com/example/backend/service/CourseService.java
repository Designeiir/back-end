package com.example.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.Course;
import com.example.backend.entity.Course;
import com.example.backend.entity.User;
import java.util.List;
import java.util.Map;

import java.util.List;

public interface CourseService {
    //查询所有课程
     List<Course> selectAllCourseServices();

    //按id查询课程
     List<Course> selectCourseById(int id);

    //分页查询
    Page<Course> test(Page<Course> page , int id);

    public Map<String, Object> selectCourse(int cid);
    public List<Map<String, Object>> selectCourseRand(int num);
}