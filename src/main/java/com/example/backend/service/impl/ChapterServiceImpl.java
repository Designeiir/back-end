package com.example.backend.service.impl;

import com.example.backend.entity.Chapter;
import com.example.backend.entity.Lecture;
import com.example.backend.mapper.ChapterMapper;
import com.example.backend.mapper.LectureMapper;
import com.example.backend.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private LectureMapper lectureMapper;

    public List<Chapter> getChapter(int cid) {
        List<Chapter> ret = chapterMapper.selectChapterByCid(cid);
        for (Chapter c: ret) {
            c.setSections(lectureMapper.selectLectureByCidAndChapter(cid, c.getNo()));
        }
        return ret;
    }

    public boolean deleteChapter(int cid, int no) {
        // firstly delete all lectures in this chapter
        if (!lectureMapper.deleteLecturesByCidAndChapter(cid, no))
            return false;
        // then delete this chapter from database
        if (!chapterMapper.deleteChapterByCidAndNo(cid, no))
            return false;

        return true;
    }

    public boolean addChapter(int cid, int no, String name) {
        // if chapter exists
        if (!chapterMapper.selectChapterByCidAndNo(cid, no).isEmpty()) {
            chapterMapper.deleteChapterByCidAndNo(cid, no);
        }
        return chapterMapper.insertChapter(cid, name, no);
    }
}
