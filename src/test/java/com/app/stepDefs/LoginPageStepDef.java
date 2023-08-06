package com.app.stepDefs;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.app.pages.LoginPage;
import com.app.utils.Utilities;

import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginPageStepDef {

	private static WebDriver driver;
	public final static int TIMEOUT = 10;
	LoginPage objLoginPage = new LoginPage(Utilities.getDriver1());
	public Scenario scenario;
	Logger log = LogManager.getFormatterLogger(LoginPageStepDef.class);

	@Given("user is on app login page {string}")
	public void userIsOnLoginPage(String url) throws InterruptedException {
		driver = Utilities.getDriver1();
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		log.info(new Throwable().getStackTrace()[0].getMethodName());
		objLoginPage.OpenURL(url);
		Thread.sleep(3000);
		WebElement el = driver.findElement(By.xpath("//button[text()='Home']"));
		Utilities.clickToElelement(el);
		Thread.sleep(3000);
	}

	@Then("user navigates to {string}")
	public void userNavigatesToPage(String url) {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		log.info(new Throwable().getStackTrace()[0].getMethodName());
		objLoginPage.OpenURL(url);
		Utilities.ts(scenario);
	}

	@Then("User logs into the system")
	public void userLogsIntoSystem() {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		log.info(new Throwable().getStackTrace()[0].getMethodName());
		objLoginPage.printTitle();
	}

	@Then("User logs out the system")
	public void userLogsOutTheSystem() {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		Assert.assertTrue(true);
	}

	@BeforeStep
	public void BeforeStep(Scenario scenario) {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		log.info(new Throwable().getStackTrace()[0].getMethodName());
		this.scenario = scenario;
	}
}
