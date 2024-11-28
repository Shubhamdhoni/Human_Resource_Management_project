package capestone_project_StarAgile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class Login_Page {

	WebDriver driver;
	
	public Login_Page(WebDriver d) {
		this.driver = d;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath ="//input[@placeholder='Username']")
	private WebElement username;
	
	@FindBy(xpath ="//input[@placeholder='Password']")
	private WebElement password;
	
	@FindBy(xpath = ("//button[@type='submit']"))
	private WebElement submit;
	
	
	public WebElement getUsername() {
		return username;
	}
	public WebElement getPassword() {
		return password;
	}
	public WebElement getSubmit() {
		return submit;
	}
	
	@Test
	public void login(String us , String ps) throws InterruptedException {
		getUsername().sendKeys(us);
		getPassword().sendKeys(ps);
		Thread.sleep(2000);
		getSubmit().click();
		System.out.println("Login successful");
	}
}
