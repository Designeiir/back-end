package com.example.backend.service;

import com.example.backend.entity.Exam;

import java.util.List;
import java.util.Map;

public interface ExamService {

    Exam getExamByCid(int cid);
    Map<String, Object> getExamQuestions(int eid);
}
