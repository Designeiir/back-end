package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.Course;
import com.example.backend.entity.CourseStudentCount;
import org.apache.ibatis.annotations.*;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    @Select("SELECT * FROM course WHERE status = 0 ORDER BY  RAND() LIMIT #{num}")
    List<Course> selectRand(int num);

    @Select("select * from course where status = 0")
    List<Course> selectAll();

    //按照学生id查询课表
    @Select("select * from course where cid in (select cid from stu_course where uid = #{sid})")
    List<Course> selectCourseBySid(int sid);

    //按照老师id查询课程
    @Select("select * from course where teacher = #{tid}")
    List<Course> selectCourseByTid(int tid);

    //分页查询   "LIMIT #{page.page}, #{page.size}"
    @Select("select * from course where cid in (select cid from stu_course where uid = #{id})"
            + "LIMIT #{page.current}, #{page.size}"
    )
    Page<Course> test(@Param("page") Page<Course> page, @Param("id") int id);



    // 查询课程所有信息
    @Select("select * from course where status=0 LIMIT #{page}, #{size}")
    List<Course> getAllCourse(int page, int size);


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
    List<Course> getCourseBySearch(int page, int size, String like);


    //删除关联表信息
    @Delete("delete from stu_course where cid= #{id}")
    void deleteC_T(int id);

    //查询未开课课程信息
    @Select("select * from course where status=1 LIMIT #{page}, #{size}")
    List<Course> getCourse(int page, int size);


    @Select("SELECT c.*, IFNULL(order_total, 0) AS studentCount " +
            "FROM course c " +
            "LEFT JOIN ( " +
            "  SELECT cid, COUNT(*) AS order_total " +
            "  FROM stu_course " +
            "  GROUP BY cid " +
            ") sc ON c.cid = sc.cid " +
            "WHERE c.status = 0 " +
            "ORDER BY studentCount  ")
    //查询课程按人数正序
    List<Course> courseCountT(int page, int size);

    @Select(" SELECT c.*, IFNULL(order_total, 0) AS studentCount " +
            "FROM course c " +
            "         LEFT JOIN ( " +
            "    SELECT cid, COUNT(*) AS order_total " +
            "    FROM stu_course " +
            "    GROUP BY cid " +
            ") sc ON c.cid = sc.cid " +
            "WHERE c.status = 0 " +
            "ORDER BY studentCount DESC ")
        //查询课程按人数倒序
    List<Course> courseCountF(int page, int size);

    //查询课程按时间正序
    @Select("select * from course where status=0 order by start_time LIMIT #{page}, #{size}")
    List<Course> courseTimeT(int page, int size);

    //查询课程按时间倒序
    @Select("select * from course where status=0 order by start_time desc LIMIT #{page}, #{size}")
    List<Course> courseTimeF(int page, int size);

    @Update("update course set status = 2 where cid=#{id}")
    void refuseCourse(int id);

    @Update("update course set status = 0 where cid=#{id}")
    void agreeCourse(int id);

}