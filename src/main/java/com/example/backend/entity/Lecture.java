package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Lecture {

    @TableId(type = IdType.AUTO)
    private int lid;
    private int cid;
    private int orde;
    private String url;
    private String name;
    private String description;
}
