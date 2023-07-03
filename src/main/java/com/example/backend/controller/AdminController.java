package com.example.backend.controller;

import com.example.backend.annotation.PassToken;
import com.example.backend.entity.Course;
import com.example.backend.entity.ResultInfo;
import com.example.backend.entity.User;
import com.example.backend.service.AdminService;
import com.example.backend.utils.ResultInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;


    @PassToken
    @GetMapping("/countByType")
    public int[] getCount(){
        return adminService.getCount();
    }

    /*@PassToken
    @GetMapping("/getAllCourse")
    public ResultInfo getAllCourses(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        Page<Course> p= new Page<>(page, size);
        Page<Course> course = adminService.getAllCourse(p);
        return ResultInfoUtils.success(course);
    }*/


    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PassToken
    @GetMapping("/getAllCourse")
    public ResultInfo getAllCourses(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {

        page=page*size;
        List<Course> coursePage = adminService.getAllCourse(page, size);
        return ResultInfoUtils.success(coursePage);
    }

    @PassToken
    @GetMapping("/getCouseBySearch")
    public ResultInfo getCourseBySearch(
            @RequestParam("page")int page,
            @RequestParam("size")int size,
            @RequestParam("like")String like
    ){
        page=page*size;
        List<Course> coursePage = adminService.getCourseBySearch(page,size,like);
        return ResultInfoUtils.success(coursePage);
    }

    //分页查询所有用户
    @PassToken
    @GetMapping("getAllUser")
    public ResultInfo getAllUser(
            @RequestParam("page")int page,
            @RequestParam("size")int size
    ){
        page=page*size;
        List<User> users = adminService.getAllUser(page,size);
        return ResultInfoUtils.success(users);
    }

}
