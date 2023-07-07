package com.example.backend.controller;

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

    @PassToken
    @GetMapping("/getCourseRecommend")
    public  ResultInfo getCourseRecommend(@RequestParam String key) {
        List<Map<String, Object>> courses = courseService.selectCourseRecommend(key);
        return ResultInfoUtils.success(courses);
    }


}
