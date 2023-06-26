package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Course {

    @TableId(type = IdType.AUTO)
    private int cid;
    private String name;
    private String description;
    private Date startTime;
    private int teacher;
    private String scoreRule;
    private int status;
}