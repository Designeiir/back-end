package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.Course;
import com.example.backend.mapper.CourseMapper;
import com.example.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Course> selectAllCourseServices() {
        return courseMapper.selectList(null);
    }

    @Override
    public List<Course> selectCourseById(int id) {
        List<Course> course=courseMapper.selectCourseById(id);
        return course;
    }

    @Override
    public Page<Course> test(Page<Course> page, int id) {
        Page<Course> course =courseMapper.test(page,id);
        return course;
    }
}
