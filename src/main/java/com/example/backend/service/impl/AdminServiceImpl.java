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

    //模糊查询课程
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

    @Override
    public void deleteUser(int id) {
        userMapper.deleteById(id);
    }

    @Override
    public void deleteCourse(int id) {
        courseMapper.deleteById(id);
        courseMapper.deleteC_T(id);
    }

    @Override
    public List<Course> getCourse(int page, int size) {
        return courseMapper.getCourse(page,size);
    }

    //课程人数正序
    @Override
    public List<Course> courseCountT(int page, int size) {
        // 获取课程列表
        List<Course> courseList = courseMapper.courseCountT(page, size);

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

    //课程人数倒序
    @Override
    public List<Course> courseCountF(int page, int size) {
        // 获取课程列表
        List<Course> courseList = courseMapper.courseCountF(page, size);

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

    //时间正序查询课程
    @Override
    public List<Course> courseTimeT(int page, int size) {
        // 获取课程列表
        List<Course> courseList = courseMapper.courseTimeT(page, size);

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

    //时间倒序查询课程
    @Override
    public List<Course> courseTimeF(int page, int size) {
        // 获取课程列表
        List<Course> courseList = courseMapper.courseTimeF(page, size);

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
    public List<User> getUserBySearch(int page, int size, String like) {
        // 获取课程列表
        List<User> user = userMapper.getUserBySearch(page, size,like);
        return user;
    }
    //年龄正序
    @Override
    public List<User> getUserAgeT(int page, int size) {
        return userMapper.getUserAgeT(page,size);
    }

    //年龄倒序
    @Override
    public List<User> getUserAgeF(int page, int size) {
        return userMapper.getUserAgeF(page,size);
    }

    //只看老师
    @Override
    public List<User> getTeacher(int page, int size) {
        return userMapper.getTeacher(page,size);
    }

    //只看学生
    @Override
    public List<User> getStudent(int page, int size) {
        return userMapper.getStudent(page,size);
    }


}
