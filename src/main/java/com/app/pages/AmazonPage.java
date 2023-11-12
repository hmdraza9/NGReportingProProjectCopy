package com.app.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.app.utils.Utilities;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class AmazonPage {

	public WebDriver driver;

	Logger log = LogManager.getFormatterLogger(AmazonPage.class);
	LoginPage objLoginPage = new LoginPage(Utilities.getDriver1());
	public Scenario scenario;

	public AmazonPage(WebDriver driver) {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		this.driver = driver;
	}

	String searchBox = "//*[@id='twotabsearchtextbox']";
	String searchResults = "//h2/a/span";
	String searchrButton = "//input[@type='submit' and @value='Go']";
	String productPageTitle = "//span[@id='productTitle' and contains(text(),'RunTimeVar')]";
	String productPagePrice = "//span[@id='productTitle' and contains(text(),'RunTimeVar')]/ancestor::div//div[@id='corePriceDisplay_desktop_feature_div']//span[@class='a-price-whole']";
	String parentWindow = "";
	String pageTitle = "";
	String productLabel = "";
	String productPagePriceLabel = "";

	public void searchOnAmazon(String keyword, Scenario scenario) {

		parentWindow = driver.getWindowHandle();

		driver.findElement(By.xpath(searchBox)).sendKeys(keyword);

		driver.findElement(By.xpath(searchrButton)).click();

		for (int i = 0; i < 8; i++) {

			driver.switchTo().window(parentWindow);
			WebElement productItem = driver.findElements(By.xpath(searchResults)).get(i);
			productLabel = productItem.getText().trim();
			productItem.click();

			for (String str : driver.getWindowHandles()) {

				if (!str.equalsIgnoreCase(parentWindow)) {
					driver.switchTo().window(str);
					productPagePriceLabel = driver
							.findElement(By.xpath(productPagePrice.replace("RunTimeVar", productLabel))).getText()
							.trim();
					pageTitle = driver.getTitle();
					log.info("page Title: " + pageTitle);
					log.info("page URL: " + driver.getCurrentUrl());
					if (driver.getTitle().contains(productLabel)) {
						ExtentCucumberAdapter.addTestStepLog("\nPage title - <b>" + pageTitle
								+ "</b> and <b> price is: " + productPagePriceLabel + "</b>");
						Utilities.ts(scenario);
						log.info("Title: " + pageTitle);
						driver.close();
					}
				}

			}
		}

	}

	@BeforeStep
	public void BeforeStep(Scenario scenario) {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		log.info(new Throwable().getStackTrace()[0].getMethodName());
		this.scenario = scenario;
	}

}
