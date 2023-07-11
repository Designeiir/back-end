package com.example.backend.service.impl;

import com.example.backend.entity.Chapter;
import com.example.backend.entity.Lecture;
import com.example.backend.mapper.LectureMapper;
import com.example.backend.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LectureServicelmpl implements LectureService {

    @Autowired
    private LectureMapper lectureMapper;

    @Override
    public void lectureInserted(int id,int order,String url, String type, String name, String description) {
        lectureMapper.lectureInserted(id,order,url, type, name, description);
    }

    @Override
    public List<Lecture> getLecture(int id) {
        return lectureMapper.getLecture(id);
    }

    @Override
    public List<Chapter> getChapter(int cid) {
        List<Chapter> ret = new ArrayList<>();
        int tmp_c = 0;
        List<Lecture> lecs = getLecture(cid);
        lecs.sort((Lecture a, Lecture b) -> {
            if (a.getOrde() != b.getOrde())
                return a.getOrde() - b.getOrde();
            else
                return a.getLid() - b.getLid();
        });
        if (lecs.size() == 0) {
            return ret;
        }
        for (Lecture lec : lecs) {
            while (tmp_c < lec.getOrde()) {
                tmp_c++;
                ret.add(new Chapter(tmp_c));
            }
            ret.get(tmp_c-1).getSections().add(lec);
        }
        return ret;
    }

    public boolean deleteLectureByLid(int lid) {
        lectureMapper.deleteLectureByLid(lid);
        return true;
    }

    public List<Lecture> selectLectureByCidAndNo(int cid, int chapter) {
        return lectureMapper.selectLectureByCidAndChapter(cid, chapter);
    }
}
