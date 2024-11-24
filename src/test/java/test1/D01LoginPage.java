package test1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class D01LoginPage {
    WebDriver driver;

    public D01LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void LoginData(String us, String ps) throws InterruptedException, IOException {
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(us);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(ps);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Wait for the dashboard to load
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.urlContains("dashboard"));

        // Capture a screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshotDir = new File("C:\\Screenshot1\\");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }
        FileHandler.copy(screenshot, new File(screenshotDir, "Screenshot1.jpeg"));
        System.out.println("Screenshot captured");

        // Assert login success
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed or URL mismatch.");
        System.out.println("Login Successful");
    }

    public void Logout() {
        if (driver.getCurrentUrl().contains("dashboard")) {
            driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
            driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
            System.out.println("Logout Successful");
        } else {
            System.out.println("Logout skipped: Not on dashboard page.");
        }
    }
}