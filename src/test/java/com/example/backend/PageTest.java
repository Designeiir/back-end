package com.example.backend;

import com.example.backend.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PageTest {

    @Autowired
    private QuestionService questionService;

    @Test
    public void testPage() {
        questionService.getQuestionsByPage(0, 3, 7, null);
    }
}
