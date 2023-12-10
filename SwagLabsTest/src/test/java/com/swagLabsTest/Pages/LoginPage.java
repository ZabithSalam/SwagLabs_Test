package com.swagLabsTest.Pages;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends Base{
	WebDriver driver;

	//constructor
	public LoginPage(WebDriver lDriver) {
		this.driver = lDriver;
		PageFactory.initElements(driver, this);

	}

	//elements
	@FindBy(id="user-name") WebElement uName;
	@FindBy(id="password")WebElement pswrd;
	@FindBy(id="login-button") WebElement loginButton;
	@FindBy(css=".app_logo") WebElement logo;
	@FindBy(css=".title") WebElement pageTitle;
	@FindBy(xpath="//div[3]/h3") WebElement error;

	public void login(Object username, Object password) {

		uName.sendKeys((String) username);
		pswrd.sendKeys((String) password);
		loginButton.click();
	}


	public String getErrorMsg() {
		return error.getText();
	}

	public String getPageTitle() {
		WebElement element = null;
		try {
			element = pageTitle;
			element.getText();
			// Perform actions with the found element, if needed
		} catch (org.openqa.selenium.NoSuchElementException e) {
			// Handle case when the element is not found
			Assert.fail("Login failed");
		}
		return pageTitle.getText();
	}
	
	public void verifyError(String isValid, String expected) throws IOException {
		if(isValid.equals("invalid")) {
			clear();
			String actual = getErrorMsg();
			if (actual.equals(expected)) {

				Assert.assertEquals(actual, expected);
			}
			else {

				CaptureScreenshot(driver, "testLogin");
				Assert.assertEquals(actual, expected);
			}
		}
	}
	
	public void verifyIsLogedInSuccessfully(String isValid, String expected) throws IOException {
		if(isValid.equals("valid")) {
			String actual = getPageTitle();
			if (actual.equals(expected)) {

				Assert.assertEquals(actual, expected);
			}
			else {

				CaptureScreenshot(driver, "testLogin");
				Assert.assertEquals(actual, expected);
			}
		}
	}

	public void isLogoDisplayed() {

		WebElement element = null;
		try {
			element = logo;
			element.isDisplayed();
			// Perform actions with the found element, if needed
		} catch (org.openqa.selenium.NoSuchElementException e) {
			// Handle case when the element is not found
			Assert.fail("Logo not found on the page");
		}

	}

	public void clear() {

		uName.sendKeys(Keys.CONTROL,"a", Keys.DELETE);
		pswrd.sendKeys(Keys.CONTROL,"a", Keys.DELETE);
	}

}
