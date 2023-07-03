package com.example.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.annotation.PassToken;
import com.example.backend.entity.Course;
import com.example.backend.entity.ResultInfo;
import com.example.backend.entity.User;
import com.example.backend.annotation.PassToken;
import com.example.backend.entity.Course;
import com.example.backend.entity.ResultInfo;

import com.example.backend.service.CourseService;
import com.example.backend.utils.ResultInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PassToken
    @GetMapping("/getAllCourse")
    public ResultInfo getAllCourse(){
        List<Course> course = courseService.selectAllCourseServices();
        System.out.println(course);
        return ResultInfoUtils.success(course);
    }

    @PassToken
    @GetMapping("/getCourseById/{id}")
    public ResultInfo getCourseById(@PathVariable("id")int id){
        List<Course> course = courseService.selectCourseById(id);
        return ResultInfoUtils.success(course);
    }

    //page 页码，size 每页条数 ， id 学生id
    @PassToken
    @GetMapping("/test")
    public ResultInfo test(
        @RequestParam("page") int page,
        @RequestParam("size") int size,
        @RequestParam("id")int id){
        Page<Course> p= new Page<>(page, size);
        Page<Course> course = courseService.test(p, id);


        return ResultInfoUtils.success(course);


    @Autowired
    private CourseService courseService;



    @PassToken
    @GetMapping("/getCourse")
    public ResultInfo getCourse(@RequestParam int cid) {
        Map<String, Object> courses = courseService.selectCourse(cid);
        return  ResultInfoUtils.success(courses);
    }


    @PassToken
    @GetMapping("/getCourseRand")
    public  ResultInfo getCourseRand(@RequestParam int num) {
        List<Map<String, Object>> courses = courseService.selectCourseRand(num);
        return ResultInfoUtils.success(courses);
    }

}
