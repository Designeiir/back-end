package com.example.backend.controller;

import com.example.backend.utils.ExcelUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/excelTest")
public class ExcelTestController {

    @GetMapping("/download")
    @ResponseBody
    public String downloadExcel() {
        List<String> data = Arrays.asList("数据1", "数据2", "数据3");

        try {
            String filePath = "E:\\desktop\\test\\excel\\test.xlsx"; // 指定Excel文件的保存路径
           ExcelUtils.createExcel(data, filePath);
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return "生成Excel失败";
        }
    }

}
