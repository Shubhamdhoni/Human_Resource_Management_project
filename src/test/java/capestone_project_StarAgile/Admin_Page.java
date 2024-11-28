package capestone_project_StarAgile;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Admin_Page {

	private WebDriver driver;
	
	public Admin_Page(WebDriver d) {
		this.driver = d;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//ul[@class='oxd-main-menu']")
	private List<WebElement> menu_item;
	
	@FindBy(xpath = "//span[text()='Admin']")
	private WebElement admin_link; 
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]")
	private WebElement username; 
	
	@FindBy(xpath = "//button[@type=\"submit\"]")
	private WebElement search_button;
	
	@FindBy(xpath = "//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']")
	private WebElement record_found;
	
	@FindBy(xpath = "//div[@class='oxd-table-filter-area']//div[2]//div[1]//div[2]//div[2]")
	private List<WebElement> drop_down;
	
	@FindBy(xpath = "//div[@role=\"listbox\"]/div/span")
	private List<WebElement> drop_down_items;
	
	@FindBy(xpath = "//div[@class='oxd-table-filter-area']//div[4]//div[2]//div[2]")
	private WebElement userStatusI;
	
	@FindBy(xpath = "//div[@class=\'oxd-select-option\'][2]")
	private WebElement userStatusEnable;
	
	@FindBy(xpath = "//div[@tabindex=\"0\"]")
	private List<WebElement> user_status_items;
	
	public WebElement getStatusEnable() {
		return userStatusEnable;
	}
	
	public WebElement getStatusI() {
		return userStatusI;
	}
	
	public List<WebElement> getUser_status_items(){
		return  user_status_items;
	}
	
	public List<WebElement> getDrop_down(){
		return drop_down;
	}
	
	public List<WebElement> getDrop_down_items(){
		return drop_down_items;
	}
	
	public WebElement getRecord_found() {
		return record_found ;
	}
	
	public WebElement getUsername() {
		return username ;
	}
	public WebElement getSearch_button() {
		return search_button  ;	
	}
	public List<WebElement>getMenu_items(){
		return menu_item;
	}
	public WebElement getAdmin_link() {
		return admin_link;
	}
	
	@Test
	public void menu_options() {
		for(WebElement m : getMenu_items())
		System.out.println(m.getText());
		System.out.println("Total number os menu items: 12");
		getAdmin_link().click();
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.contains("admin"),"Url does not match");
		System.out.println("Logged into admin page");
	}
	
	@Test
	public void searchByUserName(String un) throws InterruptedException {
		getUsername().sendKeys(un);
		getSearch_button().click();
		Thread.sleep(3000);
        System.out.println("Total records With username:"+un+"is:"+ getRecord_found().getText());
        driver.navigate().refresh();
	}
	
	@Test
	public void searchByUserRole() throws InterruptedException {
		getDrop_down().get(0).click();
		for(WebElement i : getDrop_down_items()){
			if(i.getText().contains("Admin")) {
				i.click();
				break;
			}
		}
		getSearch_button().click();
		Thread.sleep(3000);
		System.out.println("Total record with user Role Admin:"+ getRecord_found().getText());
		driver.navigate().refresh();
	}
	
	@Test
	public void serachByUserStatus() throws InterruptedException {
		Thread.sleep(3000);
		getStatusI().click();
		getStatusEnable().click();	
		getSearch_button().click();
		Thread.sleep(3000);
		System.out.println("Total record enabled:"+ getRecord_found().getText());
	}
}
	

