package test1;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class LoginClient {
	
	WebDriver driver;
	LoginPage l1;

	@Test(dataProvider = "loginData1",dataProviderClass = CreateLoginFile.class)
	public void LoginDemo(String us ,String ps) throws InterruptedException, IOException {
		l1.LoginData(us, ps);
		l1.Logout();
	}
	
	@BeforeTest
	public void BeforeTest() {
		 driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			
			l1 = new LoginPage(driver);
	}

}
