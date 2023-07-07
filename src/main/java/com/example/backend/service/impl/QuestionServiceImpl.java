package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.Question;
import com.example.backend.exception.OSException;
import com.example.backend.exception.OSExceptionEnum;
import com.example.backend.mapper.QuestionMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.QuestionService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据条件分页查询问题
     * */
    @Override
    public Page<Question> getQuestionsByPage(int pageNum, int pageSize, int tid, String query) {

        if (pageNum < 0 || pageSize < 0) {
            throw new OSException(OSExceptionEnum.PARAM_ERROR);
        }

        QueryWrapper<Question> wrapper = new QueryWrapper<>();

        if (tid > 0 && userMapper.selectById(tid) != null) {
            wrapper.eq("tid", tid);
        }
        if (query != null && (!query.equals(""))) {
            wrapper.like("stem", query);
        }

        Page<Question> page = new Page<>(pageNum, pageSize);
        questionMapper.selectPage(page, wrapper);
        System.out.println(page.getRecords());



        // page.getRecords().forEach(System.out::println);
        System.out.println(page.getCurrent());    //当前页
        System.out.println(page.getPages());    //总页数
        System.out.println(page.getSize());    //每页显示的条数
        System.out.println(page.getTotal());    //总记录数
        System.out.println(page.hasNext());    //下一页

        return page;

    }

    @Override
    public int addQuestion(Question question) {
        if (question.getStem() == null || question.getStem().equals("")) {
            throw new OSException(OSExceptionEnum.PARAM_ERROR);
        }
        if (userMapper.selectById(question.getTid()) == null) {
            throw new OSException(OSExceptionEnum.PARAM_ERROR);
        }

        return questionMapper.insert(question);
    }
}
