package com.example.backend.service;

import com.example.backend.entity.Course;
import com.example.backend.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminService {
    int[] getCount();

    List<Course> getAllCourse(int page, int size);

    List<Course> getCourseBySearch(int page ,int size,String like);

    List<User> getAllUser(int page, int size);

    void deleteUser(int id);

    void deleteCourse(int id);

    List<Course> getCourse(int page, int size);

    List<Course> courseCountT(int page, int size);
    List<Course> courseCountF(int page, int size);

    List<Course> courseTimeT(int page, int size);

    List<Course> courseTimeF(int page,int size);

    List<User> getUserBySearch(int page, int size, String like);

    List<User> getUserAgeT(int page,int size);
    List<User> getUserAgeF(int page,int size);
    List<User> getTeacher(int page,int size);
    List<User> getStudent(int page,int size);
}
