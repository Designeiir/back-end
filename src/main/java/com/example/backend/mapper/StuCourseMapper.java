package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.StuCourse;
import com.example.backend.entity.StuExam;
import com.example.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StuCourseMapper extends BaseMapper<StuCourse> {
    @Select("select * from stu_course where uid = #{uid} and cid = #{cid}")
    StuExam getByUidAndCid(int uid, int cid);
}
