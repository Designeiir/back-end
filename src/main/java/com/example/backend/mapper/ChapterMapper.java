package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Chapter;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChapterMapper extends BaseMapper<Chapter> {

    // 按照课程查询章节
    @Select("select * from chapter where cid=#{cid} order by no")
    List<Chapter> selectChapterByCid(int cid);

    @Select("select * from chapter where cid=#{cid} and no=#{no}")
    List<Chapter> selectChapterByCidAndNo(int cid, int no);

    @Delete("delete from chapter where cid=#{cid} and no=#{no}")
    boolean deleteChapterByCidAndNo(int cid, int no);

    @Insert("insert into chapter(cid,name,no) values (#{cid}, #{name}, #{no})")
    boolean insertChapter(int cid, String name, int no);
}
