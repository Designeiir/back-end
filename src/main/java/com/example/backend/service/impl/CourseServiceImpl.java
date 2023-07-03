package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.Course;
import com.example.backend.mapper.CourseMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.entity.Course;
import com.example.backend.mapper.CourseMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> selectCourse(int cid) {
        /*QueryWrapper qw = new QueryWrapper();
        qw.eq("cid", cid);
        List<Course> cour = courseMapper.selectList(qw);*/
        Course course = courseMapper.selectById(cid);
        String name = userMapper.selectById(course.getTeacher()).getName();
        Map<String, Object> map = new HashMap<>();
        map.put("course", course);
        map.put("teacher", name);

        //List<Map<String, Object>> mapList = new ArrayList<>();
        //mapList.add(map);
        return map;
    }

    @Override
    public List<Map<String, Object>> selectCourseRand(int num){

        List<Map<String, Object>> mapList = new ArrayList<>();
        List<Course> courses = courseMapper.selectRand(num);
        // List<String> names = userMappe;
        for (Course course : courses) {
            String name = userMapper.selectById(course.getTeacher()).getName();
            Map<String, Object> map = new HashMap<>();
            map.put("course", course);
            map.put("teacher", name);
            mapList.add(map);
        }
        return mapList;
    }
}
