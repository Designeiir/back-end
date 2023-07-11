package com.example.backend.controller;

import com.example.backend.annotation.PassToken;
import com.example.backend.entity.ResultInfo;
import com.example.backend.service.ChapterService;
import com.example.backend.service.LectureService;
import com.example.backend.utils.ResultInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LectureAndChapterController {

    @Autowired
    private LectureService lectureService;

    @Autowired
    private ChapterService chapterService;

    @PassToken
    @GetMapping("/getLecture")
    public ResultInfo getLecture(
            @RequestParam("cid")int cid
    ){
        return ResultInfoUtils.success(lectureService.getLecture(cid));
    }

    @PassToken
    @GetMapping("/getChapter")
    public ResultInfo getChapter(
            @RequestParam("cid")int cid
    ){
        return ResultInfoUtils.success(chapterService.getChapter(cid));
    }

    @PassToken
    @GetMapping("/addChapter")
    public ResultInfo addChapter(
            @RequestParam("cid")int cid,
            @RequestParam("no")int no,
            @RequestParam("name")String name
    ){
        return ResultInfoUtils.success(chapterService.addChapter(cid, no, name));
    }

    @PassToken
    @GetMapping("/deleteChapter")
    public ResultInfo deleteChapter(
            @RequestParam("cid")int cid,
            @RequestParam("no")int no
    ){
        return ResultInfoUtils.success(chapterService.deleteChapter(cid, no));
    }

    @PassToken
    @GetMapping("/deleteLecture")
    public ResultInfo deleteLecture(
            @RequestParam("lid")int lid
    ){
        return ResultInfoUtils.success(lectureService.deleteLectureByLid(lid));
    }

    @PassToken
    @GetMapping("/getLectureByCidAndNo")
    public ResultInfo getLectureByCidAndNo(
            @RequestParam("cid")int cid,
            @RequestParam("no")int no
    ){
        return ResultInfoUtils.success(lectureService.selectLectureByCidAndNo(cid, no));
    }
}
