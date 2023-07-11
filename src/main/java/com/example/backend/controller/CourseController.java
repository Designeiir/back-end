package com.example.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.annotation.PassToken;
import com.example.backend.entity.Course;
import com.example.backend.entity.ResultInfo;
import com.example.backend.entity.User;
import com.example.backend.annotation.PassToken;
import com.example.backend.entity.Course;
import com.example.backend.entity.ResultInfo;

import com.example.backend.exception.OSException;
import com.example.backend.exception.OSExceptionEnum;
import com.example.backend.service.CourseService;
import com.example.backend.utils.ResultInfoUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
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
    @GetMapping("/getCourseBySid/{sid}")
    public ResultInfo getCourseBySid(@PathVariable("sid")int sid){
        List<Course> course = courseService.selectCourseBySid(sid);
        return ResultInfoUtils.success(course);
    }

    @PassToken
    @GetMapping("/getCourseByTid/{tid}")
    public ResultInfo getCourseByTid(@PathVariable("tid")int tid){
        List<Course> course = courseService.selectCourseByTid(tid);
        return ResultInfoUtils.success(course);
    }

    //page 页码，size 每页条数 ， id 学生id
    @PassToken
    @GetMapping("/test")
    public ResultInfo test(
        @RequestParam("page") int page,
        @RequestParam("size") int size,
        @RequestParam("id")int id) {
        Page<Course> p = new Page<>(page, size);
        Page<Course> course = courseService.test(p, id);


        return ResultInfoUtils.success(course);
    }



    @PassToken
    @GetMapping("/getCourse")
    public ResultInfo getCourse(@RequestParam int cid, @RequestParam int uid) {
        Map<Object, Object> courses = courseService.selectCourse(cid, uid);
        return  ResultInfoUtils.success(courses);
    }

    @PassToken
    @GetMapping("/joinCourse")
    public void joinCourse(@RequestParam int cid, @RequestParam int uid) {
        courseService.joinCourse(cid, uid);
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


    @PassToken
    @GetMapping("getByTidPage")
    public ResultInfo getCourseByTidPage(@RequestParam int tid, @RequestParam int page, @RequestParam int size) {
        Page<Course> result = courseService.getByTidPage(tid, page, size);
        return ResultInfoUtils.success(result);
    }

    @PassToken
    @PostMapping("updateCourse")
    public ResultInfo updateCourse(@RequestBody Course course) {
        int result = courseService.updateCourse(course);
        if (result == 0) {
            throw new OSException(OSExceptionEnum.DATABASE_ERROR);
        }
        return ResultInfoUtils.success();
    }

}
