package com.example.backend;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.*;
import com.example.backend.mapper.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackEndApplicationTests {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private ExQueMapper exQueMapper;
    @Autowired
    private LectureMapper lectureMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private StuCourseMapper stuCourseMapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void mapperTest() {
        Course course = courseMapper.selectById(1);
        System.out.println(course);

        Exam exam = examMapper.selectById(1);
        System.out.println(exam);

        ExQue exQue = exQueMapper.selectById(1);
        System.out.println(exQue);

        Lecture lecture = lectureMapper.selectById(1);
        System.out.println(lecture);

        Question question = questionMapper.selectById(1);
        System.out.println(question);

        StuCourse stuCourse = stuCourseMapper.selectById(1);
        System.out.println(stuCourse);


    }

    @Test
    void textTest() {
        Course course = courseMapper.selectById(1);
        System.out.println(course.getStartTime());
        System.out.println(course.getDescription());
    }

    @Test
    public void selectPage(){
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        // userLambdaQueryWrapper.like(User::getUid , 1);

        Page<User> userPage = new Page<>(0 , 2);
        Page<User> userIPage = userMapper.selectPage(userPage, null);
        // IPage<User> userIPage = userMapper.selectPage(userPage , userLambdaQueryWrapper);
        System.out.println("总页数： "+userIPage.getPages());
        System.out.println("总记录数： "+userIPage.getTotal());
        userIPage.getRecords().forEach(System.out::println);
    }

    @Test
    void testPage() {
        for (User user : userMapper.selectAllByPage(0, 2)) {
            System.out.println(user);
        }

    }


}
