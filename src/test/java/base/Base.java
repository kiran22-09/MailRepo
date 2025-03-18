package base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;

public class Base {
	
	public String takeScreenShot(String testName , WebDriver driver) {
		
	File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	File destFile = new File(System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png");
	try {
		FileUtils.copyFile(srcFile, destFile);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return destFile.getAbsolutePath();
		
	}

}
