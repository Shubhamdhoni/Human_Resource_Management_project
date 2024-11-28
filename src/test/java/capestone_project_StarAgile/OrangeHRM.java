package capestone_project_StarAgile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class OrangeHRM {
	public static WebDriver driver = new ChromeDriver();

	String fpath = "C:\\Users\\Admin\\eclipse-workspace\\Human_Resource_Management_project\\loginData.xlsx";

	File file;
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;

	ExtentSparkReporter htmlreport;
	ExtentReports report;
	ExtentTest test;

	String extentreportpath = System.getProperty("user.dir") + "\\Reports\\OHRM_Login_Report_" + System.currentTimeMillis() + ".html";

	@Test(dataProvider = "loginData")
	public void Ohrmlogin(String un, String ps) throws InterruptedException {
	    driver.findElement(By.name("username")).sendKeys(un);
	    driver.findElement(By.name("password")).sendKeys(ps);
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//button[@type=\"submit\"]")).submit();
	}

	@DataProvider
	public Object[][] loginData() {
	    int totalRows = sheet.getPhysicalNumberOfRows();
	    String[][] loginData = new String[totalRows - 1][2];

	    for (int i = 0; i < totalRows - 1; i++) {
	         row = sheet.getRow(i + 1);
	        for (int j = 0; j < 2; j++) {
	             cell = row.getCell(j);
	            loginData[i][j] = cell.getStringCellValue();
	        }
	    }

	    return loginData;
	}
	@BeforeTest
	public void beforeTest() throws IOException {
	    file = new File(fpath);
	    fis = new FileInputStream(file);
	    wb = new XSSFWorkbook(fis);
	    sheet = wb.getSheetAt(0);

	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");  

	    // Extent Report initialization
	    htmlreport = new ExtentSparkReporter(extentreportpath);
	    report = new ExtentReports();
	    report.attachReporter(htmlreport);

	    // Setting environment for Extent report
	    report.setSystemInfo("Machine name:", "Dell");
	    report.setSystemInfo("Tester name:", "Shubham");
	    report.setSystemInfo("OS", "Windows 10 pro");
	    report.setSystemInfo("Browser", "Google Chrome");
	    
	 // Configuration of the report
	    htmlreport.config().setDocumentTitle("OHRM Login Report");
	    htmlreport.config().setReportName("OHRM Login Details");
	    htmlreport.config().setTheme(Theme.STANDARD);
	    htmlreport.config().setTimeStampFormat("EEEE, MM DD, yyyy HH:mm:ss");
	}
	    @AfterMethod
	    public void afterMethod() throws Exception {
	        String current_Url = driver.getCurrentUrl();
	        Assert.assertTrue(current_Url.contains(current_Url), "URL does not match");

	        if (current_Url.contains("dashboard")) {
	            System.out.println("Test Case Pass");
	            Thread.sleep(3000);
	            Utility.getScreenshot(driver);
	            test = report.createTest("OHRM Valid Login");
	            test.log(Status.PASS, MarkupHelper.createLabel("OHRM Login: Pass", ExtentColor.GREEN));
	            driver.findElement(By.xpath("//i[@class=\"oxd-icon bi-caret-down-fill oxd-userdropdown-icon\"]")).click();
	            driver.findElement(By.linkText("Logout")).click(); 
	        } else {
	            System.out.println("Test Case Fail");
	            Thread.sleep(3000);
	            Utility.getScreenshot(driver);
	            test = report.createTest("OHRM Invalid Login");
	            test.log(Status.FAIL, MarkupHelper.createLabel("OHRM Login: Fail", ExtentColor.RED));
	        }
	    }
	    @AfterTest
	    public void teardown() {
	        driver.quit();
	        report.flush();
	    }	    
	}

