package com.example.backend.controller;

import com.example.backend.annotation.PassToken;
import com.example.backend.entity.Exam;
import com.example.backend.entity.ResultInfo;
import com.example.backend.service.ExamService;
import com.example.backend.utils.ResultInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/exam")
@CrossOrigin
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping("getExam")
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
}
