package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class StuExam {

    @TableId(type = IdType.AUTO)
    private int id;  // 主键id
    private int uid; // 学生id
    private int eid; // 考试id
    private Date startTime; // 学生答卷时间
    private Date endTime; // 学生交卷时间
    private int score; // 学生该考试总成绩
}
