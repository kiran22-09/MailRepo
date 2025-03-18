package Listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.Base;
import utilities.ExtentReportGenerator;

public class MyListeners extends Base implements ITestListener{
	//Extent Reports
	ExtentReports report = ExtentReportGenerator.getExtentReport();
	ExtentTest eTest;
	
	@Override
	public void onTestStart(ITestResult result) {
		
		String testName = result.getName();
		eTest = report.createTest(testName);
		eTest.log(Status.INFO, testName+ "Execution Started");
		
	}

	//ExtentReports
	@Override
	public void onTestSuccess(ITestResult result) {
		
		String testName = result.getName();
		eTest.log(Status.PASS,testName+  "Got successfully executed");
	
	}

	
	@Override
	public void onTestFailure(ITestResult result) {
		
		String testName = result.getName();
		eTest.log(Status.FAIL,testName+  "Got Failed");
		
		WebDriver driver= null;
		
		try {
			 driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		eTest.addScreenCaptureFromPath(takeScreenShot(testName,driver), testName);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		eTest.log(Status.SKIP,testName+  "Got skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		
		report.flush();
	}
	
	

}
