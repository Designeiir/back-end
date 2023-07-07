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

    //模糊查询用户
    @Select("SELECT *\n" +
            "FROM user\n" +
            "WHERE name LIKE CONCAT('%', #{like}, '%')\n" +
            "   OR uid LIKE CONCAT('%', #{like}, '%')\n" +
            "   OR phone_number LIKE CONCAT('%', #{like}, '%')\n" +
            "   OR introduction LIKE CONCAT('%', #{like}, '%')\n" +
            "LIMIT #{page}, #{size}\n")
    List<User> getUserBySearch(int page, int size, String like);

    //年龄正序
    @Select("select * from user order by age limit #{page}, #{size}")
    List<User> getUserAgeT(int page,int size);
    //年龄倒序
    @Select("select * from user order by age desc limit #{page}, #{size} ")
    List<User> getUserAgeF(int page,int size);
    //只看老师
    @Select("select * from user where type = 2 limit #{page}, #{size}")
    List<User> getTeacher(int page,int size);
    //只看学生
    @Select("select * from user where type = 1 limit #{page}, #{size} ")
    List<User> getStudent(int page,int size);
}
