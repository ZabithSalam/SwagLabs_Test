package com.swagLabsTest.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

	public static WebDriver startApplication(WebDriver driver, String browser, String appUrl) {
		

		if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browser.equals("firefox")){

			driver = new FirefoxDriver();
		}
		else if(browser.equals("edge")){

			driver = new EdgeDriver();
		}
		else {
			System.out.println("oops! driver not found..");
		}
		
		driver.manage().window().maximize();
		driver.get(appUrl);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		return driver;
		
	}
	
	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}

}
