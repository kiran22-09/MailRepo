package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class login {
	
	WebDriver driver;
	
	@BeforeMethod
	
	public void setUp() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	
	public void loginWithValidCredentials() {
		
		
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.xpath("(//a[text()='Login'])[1]")).click();
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("amotooricap3@gmail.com");
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//li//a[text()='Edit your account information']")).isDisplayed());
     		
	}
	
	@Test(priority=2)
	
	public void loginWithInValidCredentials() {
		
		
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.xpath("(//a[text()='Login'])[1]")).click();
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("amotooricap@gmail.com");
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).isDisplayed());
        
	}
	
	@Test(priority=3)
	public void loginWithoutCredentials() {
		
		
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.xpath("(//a[text()='Login'])[1]")).click();
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("");
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("");
        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Warning: No match for E-Mail Address and/or Password.']")).isDisplayed());
        
	}

	public String generateTimeStamp() {
		
		Date date = new Date();
		return date.toString().replace(" ","_").replace(":", "_");
	
	}

}
