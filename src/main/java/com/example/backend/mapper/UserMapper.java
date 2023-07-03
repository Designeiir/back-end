package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where name = #{name}")
    User selectOneByName(String name);

    //学生人数
    @Select("select count(*)from user where type=1")
    int selectCountStu();

    //老师人数
    @Select("select count(*)from user where type=2")
    int selectCountTea();

    @Select("select *from user limit #{page}, #{size}")
    List<User> getAllUser(int page,int size);


    @Select("select * from user limit #{page}, #{size}")
    List<User> selectAllByPage(int page, int size);

    @Update("update user set img = #{url} where uid = #{uid}")
    int updateImgByUid(int uid, String url);
}
