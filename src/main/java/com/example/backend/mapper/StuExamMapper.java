package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.StuExam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StuExamMapper extends BaseMapper<StuExam> {

    @Select("select * from stu_exam where uid = #{uid} and eid = #{eid}")
    StuExam getByUidAndEid(int uid, int eid);

    @Select("select * from stu_exam where uid = #{uid} and eid = #{eid}")
    List<StuExam>  getAllByUidAndEid(int uid, int eid);

    @Update("update stu_exam set end_time = #{endTime}, score = #{score} where id = #{id}")
    int updateEndTimeAndScore(String endTime, int score, int id);
}
