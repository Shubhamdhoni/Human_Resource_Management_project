package com.org_Read_Excel_Data;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class D01_ExcelUtils {
    public static List<String[]> readExcel(String filePath, String sheetName) throws Exception {
        List<String[]> data = new ArrayList<>();
        FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        for (Row row : sheet) {
            String[] rowData = new String[2]; // Adjust size based on columns
            rowData[0] = row.getCell(0).getStringCellValue(); // Username
            rowData[1] = row.getCell(1).getStringCellValue(); // Password
            data.add(rowData);
        }

        workbook.close();
        fis.close();
        return data;
    }
}
