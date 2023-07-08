package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class StuExQue {

    @TableId(type = IdType.AUTO)
    private int id; // 主键id
    private int seid;  // 学生-考试关联表id
    private int eqid; // 考试-试题关联表id
    private int answer; // 学生对这道题的答案
    private int score; // 学生对这道题的得分
}
