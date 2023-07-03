package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.Course;
import com.example.backend.entity.CourseStudentCount;
import com.example.backend.entity.User;
import com.example.backend.mapper.CourseMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl extends ServiceImpl<CourseMapper, Course>  implements AdminService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public int[] getCount() {
        int[] arr= new int[2];
        arr[0]=userMapper.selectCountStu();
        arr[1]= userMapper.selectCountTea();
        return arr;
    }


    @Override
    public List<Course> getAllCourse(int page ,int size) {
        // 获取课程列表
        List<Course> courseList = courseMapper.getAllCourse(page, size);

        // 获取课程对应的学生数量列表
        List<CourseStudentCount> courseStudentCounts = courseMapper.getCourseStudentCounts();

        // 创建一个Map，用于存储课程ID与学生人数的对应关系
        Map<Integer, Integer> studentCountMap = new HashMap<>();
        for (CourseStudentCount courseStudentCount : courseStudentCounts) {
            studentCountMap.put(courseStudentCount.getCid(), courseStudentCount.getStudentCount());
        }

        // 设置每个课程的学生人数
        for (Course course : courseList) {
            Integer studentCount = studentCountMap.get(course.getCid());
            if (studentCount != null) {
                course.setStudentCount(studentCount);
            } else {
                course.setStudentCount(0); // 如果没有学生人数信息，默认为0
            }
        }


        return courseList;
    }
    @Override

    public List<Course> getCourseBySearch(int page ,int size,String like) {
        // 获取课程列表
        List<Course> courseList = courseMapper.getCourseBySearch(page, size,like);

        // 获取课程对应的学生数量列表
        List<CourseStudentCount> courseStudentCounts = courseMapper.getCourseStudentCounts();

        // 创建一个Map，用于存储课程ID与学生人数的对应关系
        Map<Integer, Integer> studentCountMap = new HashMap<>();
        for (CourseStudentCount courseStudentCount : courseStudentCounts) {
            studentCountMap.put(courseStudentCount.getCid(), courseStudentCount.getStudentCount());
        }

        // 设置每个课程的学生人数
        for (Course course : courseList) {
            Integer studentCount = studentCountMap.get(course.getCid());
            if (studentCount != null) {
                course.setStudentCount(studentCount);
            } else {
                course.setStudentCount(0); // 如果没有学生人数信息，默认为0
            }
        }


        return courseList;
    }

    @Override
    public List<User> getAllUser(int page, int size) {
        return userMapper.getAllUser(page,size);
    }

}
