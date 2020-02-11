package Tests;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PHPTravelsTest {
	
	public static WebDriver driver;
	
	public static String driverPath = "geckodriver.exe";
	public static String baseUrl = "https://phptravels.com/demo/";
	
	@BeforeClass
	public static void browserSetup() {
		System.setProperty("webdriver.gecko.driver", driverPath);
		//instantiate driver object
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
	}
	
	@Test
	public void loginTest() throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		driver.get(baseUrl);
		//maximize browser window
		driver.manage().window().maximize();
		//get the email and password from the demo page
		WebElement frontendCredentialElement = driver.findElement(By.xpath("//div[@class='col-md-8']"));
		String[] credentials = frontendCredentialElement.getText().split("\n");
		String email = credentials[0].split(" ")[1];
		String password = credentials[1].split(" ")[1];
		//get the front-end url
		WebElement frontendUrlElement = driver.findElement(By.xpath("//a[@class='btn btn-primary btn-lg btn-block']"));
		driver.get(frontendUrlElement.getAttribute("href"));
		//get account login url
		WebElement loginUrlElement = driver.findElement(By.xpath("//a[@class='dropdown-item active tr']"));
		driver.get(loginUrlElement.getAttribute("href"));
		//input credentials
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		String currentUrl = driver.getCurrentUrl();
		//test that page is account info
		assertEquals("Test Failed: could not login", currentUrl, "https://www.phptravels.net/account/");
	}

	
	@AfterClass
	public static void browserClose() {
		driver.quit();
	}
	
}

