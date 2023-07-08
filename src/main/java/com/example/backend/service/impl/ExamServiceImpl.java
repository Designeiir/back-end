package com.example.backend.service.impl;

import com.example.backend.entity.*;
import com.example.backend.exception.OSException;
import com.example.backend.exception.OSExceptionEnum;
import com.example.backend.mapper.CourseMapper;
import com.example.backend.mapper.ExQueMapper;
import com.example.backend.mapper.ExamMapper;
import com.example.backend.mapper.QuestionMapper;
import com.example.backend.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ExQueMapper exQueMapper;
    @Autowired
    private QuestionMapper questionMapper;


    @Override
    public Exam getExamByCid(int cid) {
        if (courseMapper.selectById(cid) == null) {
            throw new OSException(OSExceptionEnum.PARAM_ERROR);
        }

        Exam exam = examMapper.getByCidExam(cid);
        return exam;
    }

    @Override
    public Map<String, Object> getExamQuestions(int eid) {
        if (examMapper.selectById(eid) == null) {
            throw new OSException(OSExceptionEnum.PARAM_ERROR);
        }
        List<ExQue> quesitons = exQueMapper.getAllByEidExQues(eid);
        for (ExQue exQue : quesitons) {
            Question question = questionMapper.selectById(exQue.getQid());
            exQue.setQuestion(question);
        }
        int score = exQueMapper.getExamScore(eid);

        Map<String, Object> map = new HashMap<>();
        map.put("questions", quesitons);
        map.put("score", score);
        return map;
    }

}
