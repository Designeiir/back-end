package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.Course;
import com.example.backend.entity.CourseStudentCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    @Select("SELECT * FROM course  ORDER BY  RAND() LIMIT #{num}")
    List<Course> selectRand(int num);


    //按照学生id查询课表
    @Select("select * from course where cid in (select cid from stu_course where uid = #{id})")
    List<Course> selectCourseById(int id);

    //分页查询   "LIMIT #{page.page}, #{page.size}"
    @Select("select * from course where cid in (select cid from stu_course where uid = #{id})"
            +"LIMIT #{page.current}, #{page.size}"
    )
    Page<Course> test(@Param("page") Page<Course> page,@Param("id") int id);



  /*  // 查询课程所有信息
    @Select("select * from course where status=0 LIMIT #{current}, #{size}")
    List<Course> getAllCourse(MyPage<Course> page);


    // 根据课程id查询学生人数
    @Select("SELECT c.cid, COUNT(sc.uid) AS studentCount " +
            "FROM course c " +
            "LEFT JOIN stu_course sc ON c.cid = sc.cid " +
            "GROUP BY c.cid")
    List<CourseStudentCount> getCourseStudentCounts();*/

    // 查询课程所有信息
    @Select("select * from course where status=0 LIMIT #{page}, #{size}")
    List<Course> getAllCourse(int page,int size);


    // 根据课程id查询学生人数
    @Select("SELECT c.cid, COUNT(sc.uid) AS studentCount " +
            "FROM course c " +
            "LEFT JOIN stu_course sc ON c.cid = sc.cid " +
            "GROUP BY c.cid")
    List<CourseStudentCount> getCourseStudentCounts();

    //模糊查询返回用户信息
    @Select("SELECT * FROM course WHERE status=0 AND" +
            " (name LIKE CONCAT('%', #{like}, '%') " +
            "OR description LIKE CONCAT('%', #{like}, '%') " +
            "OR teacher LIKE CONCAT('%', #{like}, '%'))" +
            " LIMIT #{page}, #{size};")
    List<Course> getCourseBySearch(int page,int size,String like);


}
