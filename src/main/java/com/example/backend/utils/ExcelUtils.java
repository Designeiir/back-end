package com.example.backend.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtils {

    public static void createExcel(List<String> data, String filePath) throws IOException {
        //创建一个XSSFWorkbook对象，表示一个Excel工作簿
        Workbook workbook = new XSSFWorkbook();
        //通过workbook.createSheet("Sheet1")方法创建名为"Sheet1"的工作表
        Sheet sheet = workbook.createSheet("Sheet1");

        //循环遍历数据列表，逐行创建工作表的行,单元格
        int rowCount = 0;

        for (String rowData : data) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(rowData);
        }

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        }
    }
}
