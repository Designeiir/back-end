package com.example.backend.controller;

import com.example.backend.annotation.PassToken;
import com.example.backend.entity.Course;
import com.example.backend.entity.ResultInfo;
import com.example.backend.entity.User;
import com.example.backend.service.AdminService;
import com.example.backend.utils.ResultInfoUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
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


    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //查询所有已开课课程
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

    //模糊查询课程
    @PassToken
    @GetMapping("/getCourseBySearch")
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

    //删除用户
    @PassToken
    @DeleteMapping("/deleteUser")
    public ResultInfo deleteUser(@RequestParam("id")int id){
        adminService.deleteUser(id);
        return ResultInfoUtils.success("已成功删除");
    }

    //删除课程
    @PassToken
    @DeleteMapping("/deleteCourse")
    public ResultInfo deleteCourse(@RequestParam("id") int id) {
        adminService.deleteCourse(id);
        return ResultInfoUtils.success("已成功删除");

    }

    //查询未开课课程
    @PassToken
    @GetMapping("/getCourse")
    public ResultInfo getCourses(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {

        page=page*size;
        List<Course> coursePage = adminService.getCourse(page, size);
        return ResultInfoUtils.success(coursePage);
    }

    //课程按人数正序排
    @PassToken
    @GetMapping("/courseCountT")
    public ResultInfo courseCountT(@RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        page=page*size;
        return ResultInfoUtils.success( adminService.courseCountT(page,size));
    }

    //课程按人数倒序排
    @PassToken
    @GetMapping("/courseCountF")
    public ResultInfo courseCountF(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        page=page*size;
        return ResultInfoUtils.success(adminService.courseCountF(page,size));
    }

    //课程按创建时间正序排
    @PassToken
    @GetMapping("/courseTimeT")
    public ResultInfo courseTimeT(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        page=page*size;
        return ResultInfoUtils.success(adminService.courseTimeT(page,size));
    }

    //课程按创建时间倒序排
    @PassToken
    @GetMapping("/courseTimeF")
    public ResultInfo courseTimeF(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        page=page*size;
        return ResultInfoUtils.success(adminService.courseTimeF(page,size));
    }

    //模糊查询用户
    @PassToken
    @GetMapping("/getUserBySearch")
    public ResultInfo getUserBySearch(
            @RequestParam("page")int page,
            @RequestParam("size")int size,
            @RequestParam("like")String like
    ){
        page=page*size;
        List<User> user = adminService.getUserBySearch(page,size,like);
        return ResultInfoUtils.success(user);
    }

    //年龄正序查询用户
    @PassToken
    @GetMapping("/getUserAgeT")
    public ResultInfo getUserAgeT(
            @RequestParam("page")int page,
            @RequestParam("size")int size){
        page=page*size;
        return ResultInfoUtils.success(adminService.getUserAgeT(page,size));
    }
    //年龄倒序查询用户
    @PassToken
    @GetMapping("/getUserAgeF")
    public ResultInfo getUserAgeF(
            @RequestParam("page")int page,
            @RequestParam("size")int size){
        page=page*size;
        return ResultInfoUtils.success(adminService.getUserAgeF(page,size));
    }
    //查询老师
    @PassToken
    @GetMapping("/getTeacher")
    public ResultInfo geTeacher(
            @RequestParam("page")int page,
            @RequestParam("size")int size){
        page=page*size;
        return ResultInfoUtils.success(adminService.getTeacher(page,size));
    }
    //查询学生
    @PassToken
    @GetMapping("/getStudent")
    public ResultInfo getStudent(
            @RequestParam("page")int page,
            @RequestParam("size")int size){
        page=page*size;
        return ResultInfoUtils.success(adminService.getStudent(page,size));
    }

    //拒绝课程申请
    //同意课程申请

}
