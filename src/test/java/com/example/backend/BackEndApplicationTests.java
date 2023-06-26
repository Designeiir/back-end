package com.example.backend;

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

}
