package com.app.stepDefs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.app.pages.MoneyCtrlPage;
import com.app.utils.Utilities;

import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;

public class MoneyCtrlPageStepDef {

	MoneyCtrlPage objMoneyCtrlPage = new MoneyCtrlPage(Utilities.getDriver1());
	public Scenario scenario;
	Logger log = LogManager.getFormatterLogger(MoneyCtrlPageStepDef.class);

	@Then("fetch {string} for company {string}")
	public void userNavigatesToPage(String paramValue, String companyName) throws InterruptedException {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		log.info(new Throwable().getStackTrace()[0].getMethodName());
		objMoneyCtrlPage.fetchTodayStock(paramValue, companyName, scenario);
		Utilities.ts(scenario);
	}

	@BeforeStep
	public void BeforeStep(Scenario scenario) {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		log.info(new Throwable().getStackTrace()[0].getMethodName());
		this.scenario = scenario;
	}
	
}
