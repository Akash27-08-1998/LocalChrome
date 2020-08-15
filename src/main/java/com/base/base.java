package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.excel.reading;
import com.excel.writing;
import com.utilities.pom;

import io.qameta.allure.Step;

public class base {
	public static WebDriver driver;
	protected static Properties prop;
	static FileInputStream fis;
	public static reading r = new reading();
	public static String hubURL = "http://localhost:4444/wd/hub";
	
	//Driver invocation for multiple browser with selenium grid 
	@Step("Launching the browser step....")
	public static void launchBrowser() throws IOException, InterruptedException  {
		
	try {
			fis=new FileInputStream("C:\\Users\\ADMIN\\eclipse-workspace\\EMIcalculatorHackathon\\config-file\\config.properties");
			prop=new Properties();
			prop.load(fis);
		}
		catch(FileNotFoundException e) {
			
			System.out.println(e.getMessage());
		}
	
	//running the script in selenium grid with firefox browser
	if(prop.getProperty("mode").equalsIgnoreCase("grid")) {
		if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("marionette", true);
			cap.setBrowserName("firefox");
			cap.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL(hubURL),cap);
			
			driver.get(prop.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			
		  }
	
		//running the script in selenium grid with chrome browser
		else if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.WINDOWS);
			
			ChromeOptions op = new ChromeOptions();
			op.merge(cap);
			
			driver = new RemoteWebDriver(new URL(hubURL),op);
			
			driver.get(prop.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		  }
		
		//running the script in selenium grid with IE browser
		else if(prop.getProperty("browser").equalsIgnoreCase("IE")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("internet explorer");
			cap.setPlatform(Platform.WINDOWS);
						
			driver = new RemoteWebDriver(new URL(hubURL),cap);
			
			driver.get(prop.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		 }
		
		else {
			   System.out.println("Please choose a supported Browser");
			 }
	
	   }
	
	//running the script in selenium with firefox browser
	else if(prop.getProperty("mode").equalsIgnoreCase("local")) {
	
		if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", prop.getProperty("driverpath")+"geckodriver.exe");
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability("marionette", true);
			
			driver=new FirefoxDriver();
			
			driver.get(prop.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		}
	
		//running the script in selenium with chrome browser
		else if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", prop.getProperty("driverpath")+"chromedriver.exe");
			driver=new ChromeDriver();
			
			driver.get(prop.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		  }
		
		//running the script in selenium with IE browser
		else if(prop.getProperty("browser").equalsIgnoreCase("IE")) {
			
			System.setProperty("webdriver.ie.driver", prop.getProperty("driverpath")+"IEDriverServer.exe");
			driver=new InternetExplorerDriver();
			
			driver.get(prop.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		}
		
		else {
			    System.out.println("Please choose a supported browser");
			 }
 }
	
	else {
		    System.out.println("Please choose a supported mode");
	     }
	
	
}
	
	//Selecting the car loan tab
	@Step("Selecting the car loan section step....")
	public void carloan() throws InterruptedException {
		
		driver.findElement(pom.car).click();
		
		
	}
	
	//Entering the loan amount
	@Step("Entering the loan amount step....")
	public void enterloanAmount() throws IOException {
		
		String fileName= prop.getProperty("filename");
		String sheetName = prop.getProperty("sheetname");
		
		String loanAmount = Double.toString(r.read(fileName, sheetName, 1, 0));
		
		driver.findElement(pom.loanAmount).sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.CLEAR));
		driver.findElement(pom.loanAmount).sendKeys(loanAmount);
		
		
		
	}
	
	//Entering the loan interest rate
	@Step("Entering the interest rate step....")
	public void enterInterest() throws IOException{
		
		String fileName= prop.getProperty("filename");
		String sheetName = prop.getProperty("sheetname");
		
		String interest = Double.toString(r.read(fileName, sheetName, 1, 1));
		
		driver.findElement(pom.loanInterest).sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.CLEAR));
		driver.findElement(pom.loanInterest).sendKeys(interest);
		
	}
	
	//Entering the loan interest rate for negative testing
	@Step("Entering the interest rate step....")
	public void enterInterestForNegativeTest() throws IOException, InterruptedException{
		
		String fileName= prop.getProperty("filename");
		String sheetName = prop.getProperty("sheetname");
		
		String interest = Double.toString(r.read(fileName, sheetName, 1, 3));
		
		driver.findElement(pom.loanInterest).sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.CLEAR));
		driver.findElement(pom.loanInterest).sendKeys(interest);
		Thread.sleep(2000);
		
	}
	
	//Checking the interest rate value for negative testing
	@Step("Checking the interest rate value step....")
	public String error() {
		driver.findElement(pom.tenure).click();
		String value=driver.findElement(pom.loanInterest).getAttribute("value");
		System.out.println();
		System.out.println("The current rate of interest is: "+value);
		System.out.println();
		return value;
		
	}
	
	//Entering the loan tenure 
	@Step("Entering the tenure value step....")
	public void enterTenure() throws IOException {
		
		String fileName= prop.getProperty("filename");
		String sheetName = prop.getProperty("sheetname");
				
		String tenure = Double.toString(r.read(fileName, sheetName, 1, 2));
		
		driver.findElement(pom.tenure).sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.CLEAR));
		driver.findElement(pom.tenure).sendKeys(tenure);
		
		
	}
	
	//Generating the loan details
	@Step("Gathering the loan details step....")
	public void generate() {
		driver.findElement(pom.yr).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	//Generating the loan graph
	@Step("Viewing the loan details graph step....")
	public void scrollToGraph() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0, 1175)");
		Thread.sleep(3000);
	}
	
	//Generating the loan amount and interest rate for the first month
	@Step("Viewing the monthly principle amount and interest rate step....")
	public void ScrollToDetails() throws InterruptedException {
		WebElement ele1 = driver.findElement(pom.scroller);
		WebElement ele2 = driver.findElement(pom.expand);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()",ele1);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click()", ele2);
		Thread.sleep(3000);
	}
	
	//Writing the data into an excel file
	@Step("Gathering the monthly amount and interest rate step....")
	public void fetchDetails() throws IOException {
		String principleAmount = driver.findElement(pom.amount).getText();
		String interestRate = driver.findElement(pom.interest).getText();
		
		writing w = new writing();
		w.Write(principleAmount,interestRate);
		
	}
	
	//Closing the application
	@Step("Closing the browser step....")
	public void close() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	}
}
