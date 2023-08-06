package com.app.stepDefs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.app.pages.LoginPage;
import com.app.utils.Utilities;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;

public class dbOpsStepDefAPI {

	public final static int TIMEOUT = 10;
	Logger log = LogManager.getFormatterLogger(dbOpsStepDefAPI.class);
	LoginPage objLoginPage = new LoginPage(Utilities.getDriver1());


	@Then("^user multiplies \"(.*)\" and \"(.*)\"$")
	public void userMultipliesTwoNumbers(String num1, String num2) {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		num1 = num1.replace("<", "").replace(">", "");
		num2 = num2.replace("<", "").replace(">", "");
//		System.out.println("Number 1: "+num1+"; Number 2: "+num2);
//		System.out.println("Multiplication of numbers Number 1: "+num1+"; Number 2: "+num2+" is: "+Integer.parseInt(num1)*Integer.parseInt(num2));
		log.info("Multiplication of numbers Number 1: "+num1+"; Number 2: "+num2+" is: "+Integer.parseInt(num1)*Integer.parseInt(num2));
	}

	@Then("tests pipe separated params")
	public void testsPipeSeparatedParams(DataTable dataTable) {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		dataTable.transpose();
//		System.out.println("dataTable.height(): "+dataTable.height());
//		List<String> data = dataTable.asList(String.class);
//		for(String str : data) {
//			System.out.println("Data value: "+str);
//		}
		
	}

}
