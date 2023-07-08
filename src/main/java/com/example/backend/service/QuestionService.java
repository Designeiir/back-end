package com.example.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.Question;

import java.util.List;

public interface QuestionService {
    Page<Question> getQuestionsByPage(int pageNum, int pageSize, int tid, String query);
    int addQuestion(Question question);
}
