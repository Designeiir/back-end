package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ExQue {

    @TableId(type = IdType.AUTO)
    private int id;
    private int qid;
    private int eid;
    private int orde;
    private int score;
    @TableField(exist = false)
    private Question question;
}
