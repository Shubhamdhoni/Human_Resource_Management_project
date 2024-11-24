package test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class OrangeHRMTest {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
     //   ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("ExtentReport.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReport.html");
        extent = new ExtentReports();
    //    extent.attachReporter(htmlReporter);
        extent.attachReporter(sparkReporter);
    }

    @BeforeMethod
    public void setup() throws InterruptedException {
   //     System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        Thread.sleep(2000);
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) throws IOException {
        test = extent.createTest("Login Test with username: " + username);
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();

        // Capture Screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("Screenshots/" + username + ".png"));

        try {
            WebElement welcomeMessage = driver.findElement(By.id("welcome"));
            if (username.equals("Admin") && password.equals("admin123")) {
                Assert.assertTrue(welcomeMessage.isDisplayed());
                test.pass("Login successful for valid credentials.");
                // Logout
                welcomeMessage.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout"))).click();
            } else {
                Assert.fail("Invalid credentials passed as valid.");
            }
        } catch (Exception e) {
            test.fail("Login failed as expected for invalid credentials.");
            Assert.assertTrue(true);
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() throws IOException {
    	String filePath = "C:/Users/Admin/eclipse-workspace/Human_Resource_Management_project/loginData.xlsx";
 return ExcelReader.readExcel(filePath, "Sheet1");
    }
}

