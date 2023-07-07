package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Course;
import com.example.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    @Select("SELECT * FROM course WHERE status = 0 ORDER BY  RAND() LIMIT #{num}")
    List<Course> selectRand(int num);

    @Select("select * from course where status = 0")
    List<Course> selectAll();

}
