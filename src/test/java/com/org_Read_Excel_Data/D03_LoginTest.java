package com.org_Read_Excel_Data;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class D03_LoginTest {
    WebDriver driver;
    D02_LoginPage loginPage;

    @BeforeClass
    public void setup() {
    //    System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage = new D02_LoginPage(driver);
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) throws Exception {
        loginPage.login(username, password);
        captureScreenshot(username);

        if (username.equals("Admin") && password.equals("admin123")) {
            Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed for valid credentials");
            loginPage.logout();
        } else {
            Assert.assertFalse(loginPage.isLoginSuccessful(), "Login succeeded for invalid credentials");
        }
    }

    @DataProvider
    public Object[][] loginData() throws Exception {
    	List<String[]> data = D01_ExcelUtils.readExcel("src/test/resources/loginData.xlsx", "Sheet1");


        return data.toArray(new Object[0][]);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public void captureScreenshot(String filename) throws Exception {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File("screenshots/" + filename + ".png");
        destFile.getParentFile().mkdirs();
        org.apache.commons.io.FileUtils.copyFile(srcFile, destFile);
    }
}
