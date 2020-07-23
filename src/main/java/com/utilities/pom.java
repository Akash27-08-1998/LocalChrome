package com.utilities;

import org.openqa.selenium.By;

public class pom {
	public static final By car= By.xpath("//li[3]/a[@href='#']");
	public static final By loanAmount = By.id("loanamount");
	public static final By loanInterest = By.xpath("//*[@id='loaninterest']");
	public static final By tenure = By.xpath("//*[@id='loanterm']");
	public static final By expand= By.xpath("//*[@id='year2020']");
	public static final By amount = By.cssSelector("#monthyear2020 > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)");
	public static final By interest = By.cssSelector("#monthyear2020 > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(3)");
	public static final By scroller = By.xpath("//*[@id='yearheader' and text()='Year']");
	public static final By yr = By.cssSelector("label.active:nth-child(1)");
}
