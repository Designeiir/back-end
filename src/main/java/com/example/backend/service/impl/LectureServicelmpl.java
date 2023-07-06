package com.example.backend.service.impl;

import com.example.backend.entity.Lecture;
import com.example.backend.mapper.LectureMapper;
import com.example.backend.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
