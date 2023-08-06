package com.app.Hooks;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.app.utils.Utilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class ApplicationHooks {

	static Logger log = LogManager.getFormatterLogger(ApplicationHooks.class);
	
	@Before("@UI")
	public static void Before_UI() {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		log.info(new Throwable().getStackTrace()[0].getMethodName());
		Utilities.getDriver();
	}
	
	
	
	@After("@UI")
	public static void After_UI() throws IOException {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		log.info(new Throwable().getStackTrace()[0].getMethodName());
		Utilities.tearDown();
	}

	@Before("@DB")
	public static void setItUpdB() {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
//		System.out.println("***************Initiate dB Connection***************");
		log.info("***************Initiate dB Connection***************");
	}
	
	
	
	@After("@DB")
	public static void clearItOffdB() throws IOException {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
//		System.out.println("***************CLOSE dB Connection***************");
		log.info("***************CLOSE dB Connection***************");
	}
	
}
