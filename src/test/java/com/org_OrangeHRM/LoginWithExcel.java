package com.org_OrangeHRM;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginWithExcel {

    public static void main(String[] args) throws Throwable {
        // Path to the Excel file
        String filePath = "C:\\Users\\Admin\\eclipse-workspace\\Human_Resource_Management_project\\loginData.xlsx";

        // Load the Excel file
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assume data is in the first sheet

        // Set up WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Iterate through rows in Excel sheet
        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip the header row
            Row row = sheet.getRow(i);
            String username = row.getCell(0).getStringCellValue();
            String password = row.getCell(1).getStringCellValue();

            // Enter username and password
            driver.findElement(By.name("username")).clear();
            driver.findElement(By.name("username")).sendKeys(username);
            driver.findElement(By.name("password")).clear();
            driver.findElement(By.name("password")).sendKeys(password);
            System.out.println("Attempting login with: " + username + " / " + password);

            // Click the login button
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            // Optional: Add delay or verification after login
            Thread.sleep(2000); // Replace with explicit wait for better handling

            // Navigate back to login page if necessary
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        }

        // Close the workbook and browser
        workbook.close();
        fis.close();
        driver.quit();
    }
}


