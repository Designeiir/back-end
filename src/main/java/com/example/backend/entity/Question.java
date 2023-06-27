package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Question {

    @TableId(type = IdType.AUTO)
    private int qid;
    @TableField()
    private String stem;
    private String a;
    private String b;
    private String c;
    private String d;
    private int answer;
    private String explanation;
    private int type;
}
