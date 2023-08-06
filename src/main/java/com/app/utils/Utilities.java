package com.app.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Utilities {

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	public static WebDriver driver;
	static Logger log = LogManager.getFormatterLogger(Utilities.class);
	static JavascriptExecutor js ;

	public static WebDriver getDriver() {//Using normal driver init
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		log.info(new Throwable().getStackTrace()[0].getMethodName());
//		if (tlDriver == null) {

//			System.out.println("***************Set up new browser***************");
			log.info("***************Set up new browser***************");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
//			options.addArguments("--headless");
			options.addArguments("headless");
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(options));
//			driver = new ChromeDriver(options);
			getDriver1().manage().timeouts().implicitlyWait(Duration.ofMillis(20000));
			getDriver1().manage().window().maximize();
//			System.out.println("Driver if driver==null : "+driver);
//		}
//		System.out.println("Driver if not null: "+driver);
		try {
			getDriver1().manage().window().maximize();
		} catch (NoSuchSessionException e) {
			tlDriver = null;
			getDriver1();
		} catch (NullPointerException e) {
			tlDriver = null;
			getDriver1();
		}
//		System.out.println("Driver --> " + tlDriver);
		log.info("Driver --> " + tlDriver);
		return getDriver1();
	}

	public static synchronized WebDriver getDriver1() {//using ThreadLocal<WebDriver>
		return tlDriver.get();
	}

	public static void ts(Scenario scenario) {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		
//		System.out.println("scenario.getId(): "+scenario.getId());
		
//		System.out.println("scenario.getLine();: "+scenario.getLine());
		
//		System.out.println("scenario.getName(): "+scenario.getName());
		
//		System.out.println("scenario.getSourceTagNames(): "+scenario.getSourceTagNames());
		
//		System.out.println("scenario.getStatus(): "+scenario.getStatus());
		
//		System.out.println("scenario.getUri(): "+scenario.getUri());
		
//		System.out.println("scenario.isFailed(): "+scenario.isFailed());
		try {
			WebDriver driver = Utilities.getDriver1();
//			System.out.println("Driver value: "+driver);
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File scrFile = screenshot.getScreenshotAs(OutputType.FILE);
			String currScrPath = System.getProperty("user.dir") + "/Screenshots/" + timePrint("dd-MMM-yyyy") + "/"
					+ scenario.getName().substring(0, 10) + "_" + timePrint(null) + ".png";
			File currScr = new File(currScrPath);
			FileUtils.copyFile(scrFile, currScr);
			BufferedImage bimb = ImageIO.read(currScr);
			int height = bimb.getHeight();
			int width = bimb.getWidth();
			Reporter.log("<br><img src='" + currScrPath + "' height='" + height + "' width='" + width + "'/></br>");
			byte[] fileContent = FileUtils.readFileToByteArray(currScr);
			scenario.attach(fileContent, "image/png", null);
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void tearDown() throws IOException {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		log.info(new Throwable().getStackTrace()[0].getMethodName());
		driver = Utilities.getDriver1();
//		System.out.println("***************Kill Browser***************");
		log.info("***************Kill Browser***************");
		driver.quit();
	}

	public static String timePrint(String tFormat) {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		DateTimeFormatter dtf;
		if (tFormat != null && tFormat.contains("yyyy")) {
			dtf = DateTimeFormatter.ofPattern(tFormat);
		} else {
			dtf = DateTimeFormatter.ofPattern("ddMMMyyyy_HH-mm-ss");
		}
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now).toString();
	}
	
	public static void zoomToElelemnt(WebElement el) {
		js.executeScript("arguments[0].scrollIntoView(true);", el);
	}
	
	public static void clickToElelement(WebElement el) {
		driver = Utilities.getDriver1();
		js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", el);
		el.click();
	}


	public String timePrint() {
//		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		log.info(new Throwable().getStackTrace()[0].getMethodName());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMMyyyy_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now).toString();
	}

}
