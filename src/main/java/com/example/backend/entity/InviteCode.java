package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class InviteCode {

    @TableId(type = IdType.INPUT)
    private String code;
}
