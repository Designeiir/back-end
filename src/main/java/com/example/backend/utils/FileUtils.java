package com.example.backend.utils;

import com.example.backend.exception.OSException;
import com.example.backend.exception.OSExceptionEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
public class FileUtils {

    private static String realPath;
    private static String url;
    private static String ipPort;

    @Value("${file.realPath}")
    public void setRealPath(String path) {
        realPath = path;
    }

    @Value("${file.url}")
    public void setUrl(String baseUrl) {
        url = baseUrl;
    }

    @Value("${file.ipPort}")
    public void setIpPort(String port) {
        ipPort = port;
    }

    /**
     * 将文件保存到本地，并返回url
     * */
    public static String saveFile(MultipartFile multipartFile) {
        String newFileName = multipartFile.getOriginalFilename();
        String relativePath = "img/" + newFileName;
        File file = new File(realPath + relativePath);
        // 如果父文件夹不存在，则创建
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        //保存文件
        try {
            multipartFile.transferTo(file); // 保存文件
            return relativePath2Url(relativePath);
        } catch (Exception e) {
            throw new OSException(OSExceptionEnum.FILE_SAVE_ERROR);
        }
    }

    public static String relativePath2Url(String relativePath) {
        return ipPort + url + relativePath;
    }


}
