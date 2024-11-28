package capestone_project_StarAgile;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Client {
	public WebDriver driver;
	public Login_Page L1;
	public Admin_Page A1;
	
	@BeforeTest
	public void beforeTest() {
		driver =  new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		L1 = new Login_Page(driver);
		A1 = new Admin_Page(driver);
	}
	
	@Test
	public void testLogin_Adminpage() throws InterruptedException{
		L1.login("Admin","admin123");
		A1.menu_options();
		A1.searchByUserName("Admin");
		A1.searchByUserRole();
		A1.serachByUserStatus();
	}

	@AfterTest
	public void  afterTest() {
		driver.quit();	
	}
}
