package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Exam;
import com.example.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ExamMapper extends BaseMapper<Exam> {

    @Select("select * from exam where cid = #{cid}")
    Exam getByCidExam(int cid);

}
