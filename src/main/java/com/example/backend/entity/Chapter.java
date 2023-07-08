package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Chapter {

    @TableId(type = IdType.AUTO)
    private int cid;
    private int no;
    private String name;

    @TableField(exist = false)
    private List<Lecture> sections;

    public Chapter (int cid) {
        this.cid = cid;
        sections = new ArrayList<>();
    }
    public Chapter () {
        sections = new ArrayList<>();
    }
}
