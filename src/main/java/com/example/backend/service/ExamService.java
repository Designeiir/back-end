package com.example.backend.service;

import com.example.backend.entity.ExQue;
import com.example.backend.entity.Exam;
import com.example.backend.entity.StuExQue;

import java.util.List;
import java.util.Map;

public interface ExamService {

    Exam getExamByCid(int cid);
    Map<String, Object> getExamQuestions(int eid);
    int submitExam(int uid, int eid, List<StuExQue> answers);
    int startExam(int uid, int eid);
    int updateExam(Exam exam);
    void deleteExam(int eid);
    void updateQuestions(List<ExQue> questions);
    void insertExam(Exam exam);
}
