package com.example.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.annotation.PassToken;
import com.example.backend.entity.Question;
import com.example.backend.entity.ResultInfo;
import com.example.backend.exception.OSException;
import com.example.backend.exception.OSExceptionEnum;
import com.example.backend.service.QuestionService;
import com.example.backend.utils.ResultInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
@CrossOrigin
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/getPage")
    @PassToken
    public ResultInfo getPage(int pageNum, int pageSize, int tid, String query) {
        Page<Question> page = questionService.getQuestionsByPage(pageNum, pageSize, tid, query);
        if (page.getRecords().size() > 0) {
            return ResultInfoUtils.success(page);
        }
        else {
            throw new OSException(OSExceptionEnum.EMPTY_DATA);
        }
    }

    @PostMapping("/add")
    public ResultInfo addQuestion(@RequestBody Question question) {
        int result = questionService.addQuestion(question);
        if (result > 0) {
            return ResultInfoUtils.success();
        }
        else {
            throw new OSException(OSExceptionEnum.DATABASE_ERROR);
        }
    }
}
