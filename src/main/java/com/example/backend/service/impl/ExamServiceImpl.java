package com.example.backend.service.impl;

import com.example.backend.entity.*;
import com.example.backend.exception.OSException;
import com.example.backend.exception.OSExceptionEnum;
import com.example.backend.mapper.*;
import com.example.backend.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StuExamMapper stuExamMapper;
    @Autowired
    private StuExQueMapper stuExQueMapper;


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

    @Override
    @Transactional
    public int submitExam(int uid, int eid, List<StuExQue> answers) {
        // 判断参数
        if (userMapper.selectById(uid) == null || examMapper.selectById(eid) == null) {
            throw new OSException(OSExceptionEnum.PARAM_ERROR);
        }
        // 将该学生每道题的答题情况存入数据库

        int sumScore = 0;
        // 存入stu_ex_que表
        // 获得stu_exam关联对象
        StuExam stuExam = stuExamMapper.getByUidAndEid(uid, eid);
        for (StuExQue answer : answers) {
            // 将seid存入answer
            answer.setSeid(stuExam.getId());
            //获取每道题对应的question，根据比较得出该题的答案
            ExQue exQue = exQueMapper.selectById(answer.getEqid());
            Question question = questionMapper.selectById(exQue.getQid());
            if (question.getAnswer() == answer.getAnswer()) {
                answer.setScore(exQue.getScore());
                sumScore += exQue.getScore();
            }
            else {
                answer.setScore(0);
            }
            stuExQueMapper.insert(answer);
        }

        // 存入stu——exam表
        stuExam.setScore(sumScore);
        Date endTime = new Date();
        stuExam.setEndTime(endTime);
        int result = stuExamMapper.updateById(stuExam);

        return result;
    }

    @Override
    public int startExam(int uid, int eid) {
        // 判断参数
        if (userMapper.selectById(uid) == null || examMapper.selectById(eid) == null) {
            throw new OSException(OSExceptionEnum.PARAM_ERROR);
        }
        StuExam stuExam = new StuExam();
        stuExam.setUid(uid);
        stuExam.setEid(eid);
        Date startTime = new Date();
        stuExam.setStartTime(startTime);

        int result = stuExamMapper.insert(stuExam);
        return result;
    }

    @Override
    public int updateExam(Exam exam) {
        int result = examMapper.updateById(exam);
        return result;
    }

    @Override
    public void deleteExam(int eid) {
        Map<String, Object> map = new HashMap<>();
        map.put("eid", eid);
        exQueMapper.deleteByMap(map);

        examMapper.deleteById(eid);
    }

    @Override
    public void updateQuestions(List<ExQue> questions) {
        Map<String, Object> deleteMap = new HashMap<>();
        deleteMap.put("eid", questions.get(0).getEid());
        exQueMapper.deleteByMap(deleteMap);

        for (ExQue question : questions) {
            Map<String, Object> map = new HashMap<>();
            exQueMapper.insert(question);
        }
    }

    @Override
    public void insertExam(Exam exam) {
        if(exam.getName() == null || exam.getName().equals("") || exam.getDescription() == null || exam.getDescription().equals("")) {
            throw new OSException(OSExceptionEnum.PARAM_ERROR);
        }
        examMapper.insert(exam);
    }

}
