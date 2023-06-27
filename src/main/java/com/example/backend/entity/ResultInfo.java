package com.example.backend.entity;

import lombok.Data;

@Data
public class ResultInfo {

    private int code;
    private Object data;
    private String message;
}
