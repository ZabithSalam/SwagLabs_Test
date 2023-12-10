package com.swagLabsTest.Pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.swagLabsTest.utility.BrowserFactory;
import com.swagLabsTest.utility.ConfigDataProvider;

public class Base {

	public WebDriver driver;
	public ConfigDataProvider config = new ConfigDataProvider();

	@BeforeClass
	public void setUp() {
		driver= BrowserFactory.startApplication(driver, config.getBrowser(), config.getStagingUrl());
	}

	@AfterClass
	public void tearDown() {
		BrowserFactory.quitBrowser(driver);
	}

	public void CaptureScreenshot(WebDriver driver, String testName) throws IOException {
		TakesScreenshot screenshot = ((TakesScreenshot)driver);

		// call getScreenshotAs methods to capture image file

		File src = screenshot.getScreenshotAs(OutputType.FILE);
	    
	    // Include timestamp in the screenshot file name
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	    String timestamp = dateFormat.format(new Date());
		File srcPath=new File("."+"//Screenshots//"+testName+ "_" + timestamp + ".png");
		System.out.println("this is screenshot section");
		//copy image fle to the destination
		FileUtils.copyFile(src, srcPath);
	}
}
