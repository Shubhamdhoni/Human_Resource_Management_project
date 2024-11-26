package test2;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class C1_Demo {
	
	WebDriver driver ;
	public C1_Demo(WebDriver d) {
		driver = d;
	}
	
	@Test
	public void Login() {
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("Login successfully");
	
		List<WebElement> menu = driver.findElements(By.xpath("//ul[@class=\"oxd-main-menu\"]"));
		for(WebElement m : menu)
			System.out.println(m.getText());
	
		List<WebElement> rows = driver.findElements(By.xpath("//div[@class=\"oxd-sidepanel-body\"]"));
		System.out.println("Total options are :"+ rows.size());//ul[@class=\"oxd-main-menu\"]
	}
	
	public void ClickOnAdmin() {
		driver.findElement(By.xpath("//span[text()='Admin']")).click();
		System.out.println("Admin page opened");
	}
	
	public void searchByUserName() throws InterruptedException {
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]")).sendKeys("Admin");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		System.out.println("By User Name :"+driver.findElement(By.xpath("//span[normalize-space()='(1) Record Found']")).getText());
		driver.navigate().refresh();
	}
	
	public void searchByUserRole() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='oxd-table-filter-area']//div[2]//div[1]//div[2]//div[2]")).click();
		driver.findElement(By.xpath("//div[@role=\"option\"][2]")).click();//div[@role="option"][2]
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		System.out.println("By User Role :"+driver.findElement(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']")).getText());
		driver.navigate().refresh();
	}
	
	public void searchByUserStatus() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='oxd-table-filter-area']//div[4]//div[2]//div[2]")).click();
		driver.findElement(By.xpath("//div[@class=\"oxd-select-option\"][2]")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		System.out.println("By User Status :"+driver.findElement(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']")).getText());
	}
	
	public void Logout() {
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
		System.out.println("Logout successfully");
	}
}
