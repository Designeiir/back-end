package com.example.backend.service;

import com.example.backend.entity.Chapter;

import java.util.List;

public interface ChapterService {

    List<Chapter> getChapter(int cid);

    boolean deleteChapter(int cid, int no);

    boolean addChapter(int cid, int no, String name);
}
