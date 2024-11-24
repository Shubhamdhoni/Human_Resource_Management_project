package test1;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void LoginData(String us, String ps) throws InterruptedException, IOException {
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(us);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(ps);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenshot, new File(
				"C:\\Users\\Admin\\eclipse-workspace\\Human_Resource_Management_project\\Screenshot1"+ ".jpeg" ));
		System.out.println("Screenshot captured");

		Assert.assertTrue(driver.getCurrentUrl().contains("dashbord"), "Login Fail");
		System.out.println("Login Successfull");
	}

	public void Logout() {

		if (driver.getCurrentUrl().contains("dash")) {

			driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
			driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
			System.out.println("Logout Successfull");

		}

	}
}
