package com.app.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

public class LoginPage {

	public WebDriver driver;

	Logger log = LogManager.getFormatterLogger(LoginPage.class);
	
	public LoginPage(WebDriver driver) {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		this.driver = driver;
	}
	public void printTitle() {
//		System.out.println("Title is : --> "+driver.getTitle());
		log.info("Title is : --> "+driver.getTitle());
		ExtentCucumberAdapter.addTestStepLog("Step in report using ExtentCucumberAdapter.addTestStepLog");
	}
	

//	public void OpenURL(WebDriver driver, String url) {
	public void OpenURL(String url) {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		driver.get(url);
	}
	
}
