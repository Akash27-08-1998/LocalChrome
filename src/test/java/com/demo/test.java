package com.demo;

import java.io.IOException;

import org.apache.log4j.Logger;
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

public class test extends base {
	Logger logger = Logger.getLogger(test.class);
	ExtentReports extent;
	ExtentTest Test;
	public static screenshot ss = new screenshot();
	
	@BeforeTest(alwaysRun=true)
	public void invokeBrowser() throws IOException, InterruptedException {
		logger.info("================Launching Browser==================");
		launchBrowser();
	}
	
	@Test(priority=0)
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verify emicalculator page title")
	@Story("Story Name: To check emicalculator page title")
	
	public void regression_verifyTitle() {
		extent = new ExtentReports("C:\\Users\\ADMIN\\eclipse-workspace\\EMIcalculatorHackathon\\test-output\\Extent_RegressionReport.html",true);
		Test = extent.startTest("Verify Title for regression testing");
		Test.log(LogStatus.INFO,"regression_verifyTitle test is initiated");
		
		logger.info("==============Verifying title for regression testing==================");
		
		String actual = driver.getTitle();
		String expected = "EMI Calculator for Home Loan, Car Loan & Personal Loan in India";
		Assert.assertEquals(actual, expected,"The webpage is not Correct");
		
		Test.log(LogStatus.PASS, "The title of the webpage is verified");
		
	}
		
	@Test(priority=1)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Verify car loan button")
	@Story("Story Name: To check car loan button")
	
	public void step1() throws InterruptedException {
		Test = extent.startTest("Verify car loan button for regression");
		Test.log(LogStatus.INFO,"step1 test is initiated");
		
		logger.info("==============Selecting car loan==================");
		Assert.assertTrue(driver.findElement(pom.car).isEnabled(),"The car loan button is not enabled");
		carloan();
		
		Test.log(LogStatus.PASS, "The car loan button is verified");
		
	}
	
	@Test(priority=2)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Enter car loan amount")
	@Story("Story Name: To check car loan amount")
	
	public void step2() throws IOException {
		Test = extent.startTest("Verify car loan amount textbox for regression");
		Test.log(LogStatus.INFO,"step2 test is initiated");
		
		logger.info("==============Enter the loan amount==================");
		Assert.assertTrue(driver.findElement(pom.loanAmount).isEnabled(),"The loan amount textbox is not enabled");
		enterloanAmount();
		
		Test.log(LogStatus.PASS, "The car loan amount is set");
		
	}
		
	@Test(priority=3)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Enter car loan interest rate")
	@Story("Story Name: To check car loan interest rate")
	
	public void step3() throws IOException {
		Test = extent.startTest("Verify car loan interest rate textbox for regression");
		Test.log(LogStatus.INFO,"step3 test is initiated");
		
		logger.info("==============Enter the interest rate==================");
		Assert.assertTrue(driver.findElement(pom.loanInterest).isEnabled(),"The loan interest textbox is not enabled");
		enterInterest();
		
		Test.log(LogStatus.PASS, "The car loan interest rate is set");
		
	}
		
	@Test(priority=4)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Enter car loan tenure value")
	@Story("Story Name: To check car loan tenure")
	
	public void step4() throws IOException {
		Test = extent.startTest("Verify car loan tenure textbox for regression");
		Test.log(LogStatus.INFO,"step4 test is initiated");
		
		logger.info("==============Enter the tenure==================");
		Assert.assertTrue(driver.findElement(pom.tenure).isEnabled(),"The loan tenure textbox is not enabled");
		enterTenure();
		
		Test.log(LogStatus.PASS, "The car loan tenure value is set");
		
	}
		
	@Test(priority=5)
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Gathering loan details")
	@Story("Story Name: To check car loan details")
	
	public void step5() {
		Test = extent.startTest("Verify the car loan details generated");
		Test.log(LogStatus.INFO,"step5 test is initiated");
		
		logger.info("==============Calculating the total amount and interest==================");
		generate();
		
		Test.log(LogStatus.PASS, "The car loan details is generated");
		
	}
	
	@Test(priority=6)
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Gathering loan details graph")
	@Story("Story Name: To check car loan details graph")
	
	public void step6() throws InterruptedException {
		Test = extent.startTest("Verify the car loan details graph");
		Test.log(LogStatus.INFO,"step6 test is initiated");
		
		logger.info("==============Viewing the loan graph==================");
		scrollToGraph();
		
		Test.log(LogStatus.PASS, "The car loan details graph is generated");
		
	}
	
	@Test(priority=7)
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Taking screen shot of the car loan details graph")
	@Story("Story Name: To check car loan details graph screen shot")
	
	//Taking screenshot of the graph and adding it to the extent report
	public void step7() throws IOException {
		Test = extent.startTest("Taking the screenshot");
		Test.log(LogStatus.INFO,"step7 test is initiated");
		
		logger.info("==============Taking the screen shot of the graph==================");
		String imagePath = ss.screen(driver);
		
		Test.log(LogStatus.PASS, "The screenshot is generated");
		Test.log(LogStatus.PASS, Test.addScreenCapture(imagePath));
		
	}
	
	@Test(priority=8)
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Gathering monthly car loan details")
	@Story("Story Name: To check monthly car loan details")
	
	//Taking screenshot of the loan amount and interest rate for the first month and adding it to the extent report
	public void step8() throws IOException, InterruptedException {
		Test = extent.startTest("Verifying the monthly loan details");
		Test.log(LogStatus.INFO,"step8 test is initiated");
		
		logger.info("==============Generating the amount and interest for the first month==================");
		Assert.assertTrue(driver.findElement(pom.expand).isEnabled(),"The expand button is not enabled");
		ScrollToDetails();
		
		
		String imagePath = ss.screen(driver);
		
		Test.log(LogStatus.PASS, "The monthly loan details is generated");
		Test.log(LogStatus.PASS, Test.addScreenCapture(imagePath));
		
	}
	
	@Test(priority=9)
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Writing loan details in excel file")
	@Story("Story Name: To check car loan details in excel file")
	
	public void step9() throws IOException {
		Test = extent.startTest("Fetching the monthly loan details");
		Test.log(LogStatus.INFO,"step9 test is initiated");
		
		logger.info("==============Writing the details in excel file==================");
		Assert.assertTrue(driver.findElement(pom.amount).isDisplayed(),"The amount for the first month is not displayed");
		Assert.assertTrue(driver.findElement(pom.interest).isDisplayed(),"The interest rate for the first month is not displayed");
		fetchDetails();
		
		Test.log(LogStatus.PASS, "The monthly loan amount and interest rate is successfully stored in excel file");
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void flush(ITestResult result) {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			Test.log(LogStatus.FAIL,"The test case "+result.getName()+" is failed");
			Test.log(LogStatus.FAIL, result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			Test.log(LogStatus.SKIP,"The test case "+result.getName()+" is skipped");
		}
		
		extent.endTest(Test);
		extent.flush();
	}
	@AfterTest(alwaysRun=true)
	
	public void closing() throws InterruptedException {
		logger.info("================Closing Browser==================");
		close();
	}
}
