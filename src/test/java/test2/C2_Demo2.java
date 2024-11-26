package test2;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class C2_Demo2 {

	WebDriver driver;
	C1_Demo	D ;
	
	@Test
	public void Admin() throws InterruptedException {
		D.Login();
		D.ClickOnAdmin();
		D.searchByUserName();
		D.searchByUserRole();
		D.searchByUserStatus();
		D.Logout();
	}
	
	@BeforeTest
	public void BeforeTest() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		D = new C1_Demo(driver);
	}
}




