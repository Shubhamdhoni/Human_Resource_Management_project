package test1;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class D02LoginClient {

    WebDriver driver;
    D01LoginPage loginPage;
    ExtentReports extent;
    ExtentTest test;
   

    @Test(dataProvider = "loginData", dataProviderClass = CreateLoginFile.class)
    public void LoginDemo(String us, String ps) throws InterruptedException, IOException {
        test = extent.createTest("Login Test with User: " + us);
        try {
            loginPage.LoginData(us, ps);
            loginPage.Logout(); 
            test.pass("Test Passed: Login and Logout successful.");
        } catch (Exception e) {
            test.fail("Test Failed: " + e.getMessage());
        }
    }

    @BeforeMethod
    public void setup() {
    	
    	 // Initialize Extent Report
        ExtentSparkReporter spark = new ExtentSparkReporter("./reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    	
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new D01LoginPage(driver);
       
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        // Flush Extent Reports
        extent.flush();
      
    }
}
