package com.app.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.app.utils.Utilities;

import io.cucumber.java.Scenario;

public class MoneyCtrlPage {

	public WebDriver driver;

	Logger log = LogManager.getFormatterLogger(MoneyCtrlPage.class);
	LoginPage objLoginPage = new LoginPage(Utilities.getDriver1());

	public static String stockTableHeaders = "//div[@class='bsr_table hist_tbl_hm']/table/thead/tr/th";

	public static String stockTableCompanyColumn = "//div[@class='bsr_table hist_tbl_hm']/table/tbody//h3//a[text()='RunTimeVar1']/ancestor::tr/td[RunTimeVar2]";

	public MoneyCtrlPage(WebDriver driver) {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		this.driver = driver;
	}

	public void fetchTodayStock(String paramValue, String companyName, Scenario scenario) throws InterruptedException {

		objLoginPage.OpenURL("https://www.moneycontrol.com/stocks/marketstats/nsegainer/index.php");
		String element;
		Thread.sleep(4000);
		List<WebElement> thList = driver.findElements(By.xpath(stockTableHeaders));
		log.info("List: " + thList);
		int i = 1;
		for (WebElement el : thList) {
			if (el.getText().contains(paramValue)) {
//				log.info("el.getText(): " + el.getText());
				log.info("Header pointing at: " + i);
				break;
			} else
				i++;
		}

		element = stockTableCompanyColumn;
		if (element.contains("RunTimeVar1"))
			element = element.replace("RunTimeVar1", companyName);
		log.info("element: " + element);

		if (element.contains("RunTimeVar2"))
			element = element.replace("RunTimeVar2", String.valueOf(i));
		log.info("element: " + element);

		WebElement el = driver.findElement(By.xpath(element));
		Utilities.zoomToElelemnt(el);
		String paramValueAct = el.getText();
		log.info("paramValueAct: " + paramValueAct);

		scenario.log("Value of <b>" + paramValue + "</b> for company <b>" + companyName + "</b> is <b>" + paramValueAct
				+ "</b>");

	}

}
