package com.demo;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.base.base;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.screenshot.screenshot;
import com.utilities.pom;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class negativeTest extends base {
	Logger logger = Logger.getLogger(negativeTest.class);
	ExtentReports extent;
	ExtentTest test;
	public static screenshot ss = new screenshot();
	
	@BeforeTest
	
	public void invokeBrowser() throws IOException, InterruptedException {
		logger.info("================Launching Browser==================");
		launchBrowser();
	}
	
	@Test(priority=0)
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verify emicalculator page title for negative testing")
	@Story("Story Name: To check emicalculator page title")
	
	public void verifyTitle() {
		
		extent = new ExtentReports("C:\\Users\\ADMIN\\eclipse-workspace\\EMIcalculatorHackathon\\test-output\\Extent_NegativeReport.html",true);
		test = extent.startTest("Verify Title for Negative testing");
		test.log(LogStatus.INFO,"verifyTitle test is initiated");
		
		
		logger.info("==============Verifying title for Negative testing==================");
		
		String actual = driver.getTitle();
		String expected = "EMI Calculator for Home Loan, Car Loan & Personal Loan in India";
		Assert.assertEquals(actual, expected,"The webpage is not Correct");
		
		test.log(LogStatus.PASS, "The title of the webpage is verified");
		
	}
	
	@Test(priority=1)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Verify car loan button for negative testing")
	@Story("Story Name: To check car loan button")
	
	public void step1() throws InterruptedException {

		test = extent.startTest("Verify car loan button");
		test.log(LogStatus.INFO,"step1 test is initiated");
		
		logger.info("==============Selecting car loan for negative testing==================");
		Assert.assertTrue(driver.findElement(pom.car).isEnabled(),"The car loan button is not enabled");
		carloan();
		
		test.log(LogStatus.PASS, "The car loan button is verified");
	}
	
	@Test(priority=2)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Enter car loan amount for negative testing")
	@Story("Story Name: To check car loan amount")
	
	public void step2() throws InterruptedException, IOException {
		
		test = extent.startTest("Verify car loan amount textbox");
		test.log(LogStatus.INFO,"step2 test is initiated");
		
		logger.info("==============Enter the loan amount for negative testing==================");
		Assert.assertTrue(driver.findElement(pom.loanAmount).isEnabled(),"The loan amount textbox is not enabled");
		enterloanAmount();
		
		test.log(LogStatus.PASS, "The car loan amount is set");
		
	}
	
	@Test(priority=3)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Enter car loan interest rate for negative testing")
	@Story("Story Name: To check car loan interest rate")
	
	public void step3() throws InterruptedException, IOException {
		
		test = extent.startTest("Verify car loan interest rate textbox");
		test.log(LogStatus.INFO,"step3 test is initiated");
		
		logger.info("==============Enter the interest rate for negative testing==================");
		Assert.assertTrue(driver.findElement(pom.loanInterest).isEnabled(),"The loan interest textbox is not enabled");

		enterInterestForNegativeTest();
		
		String imagePath = ss.screen(driver);
		test.log(LogStatus.PASS, test.addScreenCapture(imagePath));
		Thread.sleep(2000);
		String value=error();
		if(!value.contains("0")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'",driver.findElement(pom.loanInterest));
		}
		Assert.assertTrue(value.contains("0"),"The interest rate is not 0");	
		test.log(LogStatus.PASS, "The car loan interest rate is set");
	}
	
	@Test(priority=4)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Enter car loan tenure value for negative testing")
	@Story("Story Name: To check car loan tenure")
	
	public void step4() throws InterruptedException, IOException {
		
		test = extent.startTest("Verify car loan tenure textbox");
		test.log(LogStatus.INFO,"step4 test is initiated");
		
		logger.info("==============Enter the tenure for negative testing==================");
		Assert.assertTrue(driver.findElement(pom.tenure).isEnabled(),"The loan tenure textbox is not enabled");
		enterTenure();
		test.log(LogStatus.PASS, "The car loan tenure value is set");
				
	}
	
	@AfterMethod
	public void flush(ITestResult result) throws IOException, InterruptedException {
		if(result.getStatus()==ITestResult.FAILURE) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("alert('The Loan Interest Is Not Set To 0')");
			Thread.sleep(2000);
			driver.switchTo().alert().accept();
			test.log(LogStatus.FAIL,"The test case "+result.getName()+" is failed");
			test.log(LogStatus.FAIL, result.getThrowable());
			String picPath = ss.screen(driver);
			test.log(LogStatus.FAIL, test.addScreenCapture(picPath));
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			test.log(LogStatus.SKIP,"The test case "+result.getName()+" is skipped");
		}
		extent.endTest(test);
		extent.flush();
	}
	@AfterTest
	
	public void closing() throws InterruptedException {
		logger.info("================Closing Browser==================");
		close();
	}
	
}
