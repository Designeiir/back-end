package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.Course;
import com.example.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    //按照学生id查询课表
    @Select("select * from course where cid in (select cid from stu_course where uid = #{id})")
    List<Course> selectCourseById(int id);

    //分页查询   "LIMIT #{page.page}, #{page.size}"
    @Select("select * from course where cid in (select cid from stu_course where uid = #{id})"
            +"LIMIT #{page.current}, #{page.size}"
    )
    Page<Course> test(@Param("page") Page<Course> page,@Param("id") int id);
}
