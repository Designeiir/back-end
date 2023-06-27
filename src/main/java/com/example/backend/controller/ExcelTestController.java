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
        List<String> data = Arrays.asList("����1", "����2", "����3");

        try {
            String filePath = "E:\\desktop\\test\\excel\\test.xlsx"; // ָ��Excel�ļ��ı���·��
           ExcelUtils.createExcel(data, filePath);
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return "����Excelʧ��";
        }
    }

}
