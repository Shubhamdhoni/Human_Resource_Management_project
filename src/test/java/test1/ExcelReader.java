package test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelReader {

    @Test
    public void dataRead() throws IOException {
        // Define the file path
        String filePath = "C:\\Users\\Admin\\eclipse-workspace\\Human_Resource_Management_project\\LoginData1.xlsx";
        File file = new File(filePath);

        // Ensure resources are closed using try-with-resources
        try (FileInputStream fis = new FileInputStream(file); XSSFWorkbook wb = new XSSFWorkbook(fis)) {
            // Get the "login" sheet
            XSSFSheet sheet = wb.getSheet("login");

            if (sheet == null) {
                System.out.println("Sheet 'login' not found in the Excel file.");
                return;
            }

            // Get the number of rows and cells
            int numRows = sheet.getPhysicalNumberOfRows();
            int numCells = sheet.getRow(0).getPhysicalNumberOfCells();

            // Iterate over rows and cells to read data
            for (int i = 0; i < numRows; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null) continue; // Skip null rows

                for (int j = 0; j < numCells; j++) {
                    XSSFCell cell = row.getCell(j);
                    if (cell == null) {
                        System.out.print("NULL\t");
                    } else {
                        System.out.print(cell.toString() + "\t");
                    }
                }
                System.out.println();
            }
        }
    }
}
