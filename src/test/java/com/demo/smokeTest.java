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

public class smokeTest extends base {
	Logger logger = Logger.getLogger(smokeTest.class);
	ExtentReports extent;
	ExtentTest test;

	@BeforeTest(alwaysRun=true)
	public void invokeBrowser() throws IOException, InterruptedException {
		logger.info("================Launching Browser==================");
		launchBrowser();
	}

	@Test(priority=0)
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verify emicalculator page title for smoke testing")
	@Story("Story Name: To check emicalculator page title")
	
	public void smoke_verifyTitle() {
		
		extent = new ExtentReports("C:\\Users\\ADMIN\\eclipse-workspace\\EMIcalculatorHackathon\\test-output\\Extent_SmokeReport.html",true);
		test = extent.startTest("Verify Title for smoke testing");
		test.log(LogStatus.INFO,"smoke_verifyTitle test is initiated");
		
		logger.info("============== Verifying title for smoke testing ==================");
		
		String actual = driver.getTitle();
		String expected = "EMI Calculator for Home Loan, Car Loan & Personal Loan in India";
		Assert.assertEquals(actual, expected,"The webpage is not Correct");
		System.out.println();
		System.out.println("The title of the webpage is correct "+actual);
		System.out.println();
	
		test.log(LogStatus.PASS, "The title of the webpage is verified");
		
	}
	
	@Test(priority=1)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Checking if car loan button is enabled")
	@Story("Story Name: To check car loan button")
	
	public void smoke1() throws InterruptedException {
		test = extent.startTest("Verify car loan button for smoke");
		test.log(LogStatus.INFO,"smoke1 test is initiated");
		
		logger.info("============== Checking car loan button ==================");
		Assert.assertTrue(driver.findElement(pom.car).isEnabled(),"The car loan button is not enabled");
		if(driver.findElement(pom.car).isEnabled()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid green'",driver.findElement(pom.car));
		}
		System.out.println();
		System.out.println("Is car loan tab enabled? "+driver.findElement(pom.car).isEnabled());
		System.out.println();
		carloan();
		test.log(LogStatus.PASS, "The car loan button is verified");
	}
	
	@Test(priority=2)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Checking if car loan amount textbox is enabled")
	@Story("Story Name: To check car loan amount textbox")
	
	public void smoke2() {
		test = extent.startTest("Verify car loan amount textbox for smoke");
		test.log(LogStatus.INFO,"smoke2 test is initiated");
		
		logger.info("============== Checking the loan amount textbox ==================");
		Assert.assertTrue(driver.findElement(pom.loanAmount).isEnabled(),"The loan amount textbox is not enabled");
		if(driver.findElement(pom.loanAmount).isEnabled()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid green'",driver.findElement(pom.loanAmount));
		}
		System.out.println();
		System.out.println("Is car loan amount textbox enabled? "+driver.findElement(pom.loanAmount).isEnabled());
		System.out.println();
		
		test.log(LogStatus.PASS, "The car loan amount textbox is enabled");
	}

	@Test(priority=3)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Checking if car loan interest rate textbox is enabled")
	@Story("Story Name: To check car loan interest rate textbox")
	
	public void smoke3() {
		test = extent.startTest("Verify car loan interest rate textbox for smoke");
		test.log(LogStatus.INFO,"smoke3 test is initiated");
		
		logger.info("============== Checking the interest rate textbox ==================");
		Assert.assertTrue(driver.findElement(pom.loanInterest).isEnabled(),"The loan interest textbox is not enabled");
		if(driver.findElement(pom.loanInterest).isEnabled()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid green'",driver.findElement(pom.loanInterest));
		}
		System.out.println();
		System.out.println("Is car loan interest rate textbox enabled? "+driver.findElement(pom.loanInterest).isEnabled());
		System.out.println();
		
		test.log(LogStatus.PASS, "The car loan interest rate textbox is enabled");
	}
	
	@Test(priority=4)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Checking if car loan tenure textbox is enabled")
	@Story("Story Name: To check car loan tenure textbox")
	
	public void smoke4() {
		test = extent.startTest("Verify car loan tenure textbox for smoke");
		test.log(LogStatus.INFO,"smoke4 test is initiated");
		
		logger.info("============== Checking the tenure textbox ==================");
		Assert.assertTrue(driver.findElement(pom.tenure).isEnabled(),"The loan tenure textbox is not enabled");
		if(driver.findElement(pom.tenure).isEnabled()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid green'",driver.findElement(pom.tenure));
		}
		
		System.out.println();
		System.out.println("Is car loan tenure textbox enabled? "+driver.findElement(pom.tenure).isEnabled());
		System.out.println();
		test.log(LogStatus.PASS, "The car loan tenure textbox is enabled");
		
	}
	
	@Test(priority=5)
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Taking the ScreenShot")
	@Story("Story Name: To take the ScreenShot")
	public void screenshot() throws IOException, InterruptedException {
		test = extent.startTest("ScreenShot for Smoke test");
		test.log(LogStatus.INFO,"screenshot test is initiated");
		
		logger.info("============== Taking the ScreenShot ==================");
		screenshot ss = new screenshot();
		Thread.sleep(2000);
		String imagePath=ss.screen(driver);
		test.log(LogStatus.PASS, test.addScreenCapture(imagePath));
	}
	
	@AfterMethod(alwaysRun=true)
	public void flush(ITestResult result) {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(LogStatus.FAIL,"The test case "+result.getName()+" is failed");
			test.log(LogStatus.FAIL, result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			test.log(LogStatus.SKIP,"The test case "+result.getName()+" is skipped");
		}
		
		extent.endTest(test);
		extent.flush();
	}
	@AfterTest(alwaysRun=true)
	
	public void closing() throws InterruptedException {
		logger.info("================Closing Browser==================");
		close();
	}

}
