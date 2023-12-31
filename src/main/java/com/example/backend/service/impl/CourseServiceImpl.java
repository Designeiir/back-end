package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.*;
import com.example.backend.exception.OSException;
import com.example.backend.exception.OSExceptionEnum;
import com.example.backend.mapper.CourseMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.entity.Course;
import com.example.backend.entity.StuCourse;
import com.example.backend.mapper.CourseMapper;
import com.example.backend.mapper.StuCourseMapper;
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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;



@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StuCourseMapper stuCourseMapper;

    @Override
    public List<Course> selectAllCourseServices() {
        return courseMapper.selectList(null);
    }

    @Override
    public List<Course> selectCourseBySid(int sid) {
        return courseMapper.selectCourseBySid(sid);
    }

    @Override
    public List<Course> selectCourseByTid(int tid) {
        List<Course> courses = courseMapper.selectCourseByTid(tid);
        return courses;
    }

    @Override
    public Page<Course> test(Page<Course> page, int id) {
        return courseMapper.test(page, id);
    }

    @Override
    public Map<Object, Object> selectCourse(int cid, int uid) {
        /*QueryWrapper qw = new QueryWrapper();
        qw.eq("cid", cid);
        List<Course> cour = courseMapper.selectList(qw);*/
        Course course = courseMapper.selectById(cid);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        String strDate1 = sdf1.format(course.getStartTime());
        course.setStartTimeStr(strDate1);


        String name = userMapper.selectById(course.getTeacher()).getName();
        Map<String, String> info = new HashMap<>();
        info.put("teacher", name);
        if (stuCourseMapper.getByUidAndCid(uid, cid) == null) {
            info.put("join", "unjoined");
        }
        else {
            info.put("join", "joined");
        }
        Map<Object, Object> map = new HashMap<>();
        map.put("course", course);
        map.put("info", info);

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
            String des = course.getDescription();
            if(des.length() > 30) {
                StringBuilder sb = new StringBuilder(des);
                StringBuilder str = sb.replace(30, des.length(), "……");
                course.setDescription(str.toString());

            }

            String name = userMapper.selectById(course.getTeacher()).getName();
            Map<String, Object> map = new HashMap<>();
            map.put("course", course);
            map.put("teacher", name);
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public void joinCourse(int cid, int uid) {
        StuCourse stuCourse = new StuCourse();
        stuCourse.setCid(cid);
        stuCourse.setUid(uid);
        stuCourseMapper.insert(stuCourse);
    }


    @Override
    public List<Map<String, Object>> selectCourseRecommend(String key) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<Course> courses = courseMapper.selectAll();
        for (Course course : courses) {
            course.setScore(getScore(key, course));
        }
        for(int i = 0, len = courses.size(); i < len; i++) {
            if(courses.get(i).getScore() == 0) {
                courses.remove(i);
                len--;
                i--;
            }
        }
        courses = courses.stream().sorted(Comparator.comparing(Course::getScore)).collect(Collectors.toList());
        Collections.reverse(courses);
        for (Course course : courses) {
            String des = course.getDescription();
            if(des.length() > 30) {
                StringBuilder sb = new StringBuilder(des);
                StringBuilder str = sb.replace(30, des.length(), "……");
                course.setDescription(str.toString());
            }

            String name = userMapper.selectById(course.getTeacher()).getName();
            Map<String, Object> map = new HashMap<>();
            map.put("course", course);
            map.put("teacher", name);
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public Page<Course> getByTidPage(int tid, int pageNum, int pageSize) {
        if (pageNum < 0 || pageSize <= 0) {
            throw new OSException(OSExceptionEnum.PARAM_ERROR);
        }

        User user = userMapper.selectById(tid);
        if (user == null) {
            throw new OSException(OSExceptionEnum.USER_EMPTY);
        }

        QueryWrapper<Course> wrapper = new QueryWrapper<>();

        wrapper.eq("teacher", tid);
        Page<Course> page = new Page<>(pageNum, pageSize);
        courseMapper.selectPage(page, wrapper);
        System.out.println(page.getRecords());

        return page;
    }

    @Override
    public void refuseCourse(int id) {
        courseMapper.refuseCourse(id);

    }

    @Override
    public void agreeCourse(int id) {
        courseMapper.agreeCourse(id);
    }


    public double getScore(String key, Course course) {
        if(course.getDescription().length() > 20) {
            return getSimi(key, course.getName()) * 2 + getSimi(key, course.getDescription().substring(0, 19)) * 1;
        }
        return getSimi(key, course.getName()) * 2 + getSimi(key, course.getDescription()) * 1;
    }

    public double getSimi(String str1, String str2) {
        int length1 = str1.length();
        int length2 = str2.length();
        int[][] dif = new int[length1 + 1][length2 + 1];
        for (int a = 0; a <= length1; a++) {

            dif[a][0] = a;

        }

        for (int a = 0; a <= length2; a++) {

            dif[0][a] = a;

        }

        int temp;

        for (int i = 1; i <= length1; i++) {

            for (int j = 1; j <= length2; j++) {

                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {

                    temp = 0;

                } else {

                    temp = 1;

                }


                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,

                        dif[i - 1][j] + 1);

            }

        }



        double similarity = 1 - (double) dif[length1][length2] / Math.max(str1.length(), str2.length());

        return similarity;
    }


    int min (int a, int b, int c) {
        if(a > b) {
            if(b > c) {
                return c;
            }
            else {
                return b;
            }
        }
        else {
            if(a > c) {
                return c;
            }
            else {
                return a;
            }
        }

    }

    @Override
    public int updateCourse(Course course) {
        if (course.getCid() == 0 || courseMapper.selectById(course.getCid()) == null) {
            throw new OSException(OSExceptionEnum.PARAM_ERROR);
        }
        int result = courseMapper.updateById(course);
        return result;
    }
}
