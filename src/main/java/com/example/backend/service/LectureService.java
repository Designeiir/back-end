package com.example.backend.service;

import com.example.backend.entity.Chapter;
import com.example.backend.entity.Lecture;

import java.util.List;

public interface LectureService {
    void lectureInserted(int id,int order,String url,String type ,String name,String description);

    List<Lecture> getLecture(int id);

    List<Chapter> getChapter(int cid);
}
