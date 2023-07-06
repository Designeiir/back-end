package com.example.backend.controller;
import com.example.backend.annotation.PassToken;
import com.example.backend.entity.ResultInfo;
import com.example.backend.entity.VideoInfo;
import com.example.backend.service.LectureService;
import com.example.backend.utils.ResultInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;


import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
public class VideoUploadController {

    @Autowired
    private LectureService lectureService;

    //处理文件，并存链接到数据库
    @PassToken
    @PostMapping("/upload")
    public ResultInfo uploadVideo(
            //@RequestPart("file") MultipartFile file,
            @RequestPart("file") MultipartFile file,
            @RequestParam("courseId") String courseId,
            @RequestParam("chapterId") String chapterId,
            @RequestParam("name") String name,
            @RequestParam("description") String description
    ) {
        // 构建视频存储路径
        String storagePath = "E:/desktop/视频存放/" + courseId + "/";

        try {
            // 创建目录（如果不存在）
            Path directoryPath = Paths.get(storagePath);
            Files.createDirectories(directoryPath);

            // 构建目标文件路径
            String fileName = file.getOriginalFilename();
            Path targetPath = directoryPath.resolve(fileName);

            // 存储文件
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            // 获取文件类型
            String type = file.getContentType();

           /* // 获取videoInfo中的name和description
            String name = videoInfo.getName();
            String description = videoInfo.getDescription();*/

            //文件链接
            String url = "http://localhost:10100/uploads/" + courseId + "/" + fileName;

            //向数据库传输
            int id = Integer.parseInt(courseId);
            int order = Integer.parseInt(chapterId);
            lectureService.lectureInserted(id, order, url, type, name, description);


            // 返回视频的访问链接或文件保存路径

            return ResultInfoUtils.success("上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            // 处理文件存储失败的情况
            return ResultInfoUtils.error(500, "文件上传失败");
        }
    }

    @PassToken
    @GetMapping("/getLecture")
    public ResultInfo getLecture(
            @RequestParam("cid")int cid
    ){
        return ResultInfoUtils.success(lectureService.getLecture(cid));


    }


}
