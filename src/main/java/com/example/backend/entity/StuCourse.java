package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class StuCourse {

    @TableId(type = IdType.AUTO)
    private int id;
    private int cid;
    private int uid;
}
