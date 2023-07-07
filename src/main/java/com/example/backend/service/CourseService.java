package com.example.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.Course;
import java.util.List;
import java.util.Map;

public interface CourseService {
    //查询所有课程
     List<Course> selectAllCourseServices();

    //按id查询课程
    List<Course> selectCourseBySid(int sid);

    List<Course> selectCourseByTid(int tid);

    //分页查询
    Page<Course> test(Page<Course> page , int id);

     Map<String, Object> selectCourse(int cid);
     List<Map<String, Object>> selectCourseRand(int num);

     void refuseCourse(int id);

    void agreeCourse(int id);


}