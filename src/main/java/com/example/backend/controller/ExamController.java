package com.example.backend.controller;

import com.example.backend.annotation.PassToken;
import com.example.backend.entity.Exam;
import com.example.backend.entity.ResultInfo;
import com.example.backend.entity.StuExQue;
import com.example.backend.exception.OSException;
import com.example.backend.exception.OSExceptionEnum;
import com.example.backend.service.ExamService;
import com.example.backend.utils.ResultInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exam")
@CrossOrigin
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping("getExam")
    @PassToken
    public ResultInfo getExam(@RequestParam int cid) {
        Exam exam = examService.getExamByCid(cid);
        return ResultInfoUtils.success(exam);
    }

    @GetMapping("getQuestion")
    @PassToken
    public ResultInfo getQuestion(@RequestParam int eid) {
        Map<String, Object> result = examService.getExamQuestions(eid);
        return ResultInfoUtils.success(result);
    }

    @PostMapping("submit")
    @PassToken
    public ResultInfo submit(@RequestParam int uid, @RequestParam int eid, @RequestBody List<StuExQue> answers) {
        int i = examService.submitExam(uid, eid, answers);
        return ResultInfoUtils.success();
    }

    @GetMapping("startExam")
    @PassToken
    public ResultInfo startExam(@RequestParam int uid, @RequestParam int eid) {
        int result = examService.startExam(uid, eid);
        if (result <= 0) {
            throw new OSException(OSExceptionEnum.DATABASE_ERROR);
        }
        else {
            return ResultInfoUtils.success();
        }
    }
}
