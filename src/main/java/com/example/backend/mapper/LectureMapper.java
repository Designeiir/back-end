package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Lecture;
import com.example.backend.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LectureMapper extends BaseMapper<Lecture> {

    //插入lecture表数据
    @Insert("insert into lecture(cid,orde,url,name,description,Type) values (#{id},#{order},#{url},#{name},#{description},#{type})")
    void lectureInserted(int id,int order,String url,String type,String name,String description);

    //根据课程id得到课件信息
    @Select("select * from lecture where cid=#{id}")
    List<Lecture> getLecture(int id);

}
