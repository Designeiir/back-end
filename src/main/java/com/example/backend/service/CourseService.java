package com.example.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.Course;
import com.example.backend.entity.Course;
import com.example.backend.entity.User;

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

    public Map<Object, Object> selectCourse(int cid, int uid);

    public void joinCourse(int cid, int uid);

    public List<Map<String, Object>> selectCourseRand(int num);

    Page<Course> getByTidPage(int tid, int pageNum, int pageSize);


     void refuseCourse(int id);

    void agreeCourse(int id);

    int updateCourse(Course course);


    public List<Map<String, Object>> selectCourseRecommend(String key);
}