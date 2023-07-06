package com.example.backend.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Chapter {
    private int id;
    private List<Lecture> sections;

    public Chapter (int id) {
        this.id = id;
        sections = new ArrayList<>();
    }
    public Chapter () {
        sections = new ArrayList<>();
    }
}
