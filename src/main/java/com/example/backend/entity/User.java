package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {

    @TableId(type = IdType.AUTO)
    private int uid;
    private String name;
    private int sex;
    private int age;
    private String phoneNumber;
    private String introduction;
    private String img;
    private int type;
    private String password;
}
