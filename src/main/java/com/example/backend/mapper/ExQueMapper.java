package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.ExQue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExQueMapper extends BaseMapper<ExQue> {

    @Select("select * from ex_que where eid=#{eid}")
    List<ExQue> getAllByEidExQues(int eid);

    @Select("select sum(score) from ex_que where eid=#{eid}")
    int getExamScore(int eid);


}
