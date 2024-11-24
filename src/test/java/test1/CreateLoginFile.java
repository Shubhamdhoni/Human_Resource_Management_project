package test1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateLoginFile {

    // File path for the Excel file
    String fPath = "C:\\Users\\Admin\\eclipse-workspace\\Human_Resource_Management_project\\LoginData1.xlsx";
    File file;
    FileOutputStream fos;
    XSSFWorkbook wb;
    XSSFSheet sheet;
    XSSFRow row;
    XSSFCell cell;
    int i = 0;

    // Test method to write data into the Excel sheet
    @Test(dataProvider = "loginData")
    public void createLoginData(String un, String ps) {
        row = sheet.createRow(i);
        cell = row.createCell(0);
        cell.setCellValue(un);
        cell = row.createCell(1);
        cell.setCellValue(ps);
        i++;
    }

    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
            {"Admin", "admin123"},
            {"shubham", "shu"},
            {"praveen", "prv"},
            {"shrishail", "shri"},
            {"aditya", "adi"},
        };
    }

    @BeforeTest
    public void beforeTest() throws FileNotFoundException {
        file = new File(fPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs(); // Create parent directories if they do not exist
        }
        fos = new FileOutputStream(file);
        wb = new XSSFWorkbook();
        sheet = wb.createSheet("login");
    }

    @AfterTest
    public void afterTest() throws IOException {
        wb.write(fos);
        wb.close();
        fos.close();
    }
}
