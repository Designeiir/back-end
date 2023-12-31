package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @TableField(exist = false)
    private int studentCount;
    private String image;
    @TableField(exist = false)
    private String startTimeStr;
    @TableField(exist = false)
    private double score = 0.0;
}
