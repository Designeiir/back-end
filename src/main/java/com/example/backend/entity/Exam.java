package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Exam {

    @TableId(type = IdType.AUTO)
    private int eid;
    private int cid;
    private Date startTime;
    private Date endTime;
    private String description;

}
