package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Lecture;
import com.example.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LectureMapper extends BaseMapper<Lecture> {

}