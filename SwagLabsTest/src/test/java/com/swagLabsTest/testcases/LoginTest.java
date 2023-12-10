package com.swagLabsTest.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.swagLabsTest.Pages.Base;
import com.swagLabsTest.Pages.LoginPage;
import com.swagLabsTest.utility.ReadExcelData;

public class LoginTest extends Base{
	
	String fileName=System.getProperty("user.dir")+"\\TestData\\testData.xlsx";

	@Test(priority = 1, dataProvider = "LoginDataProvider")
	public void testLogin(String username, String password, String expected, String isValid) throws IOException {
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);
		lp.verifyError(isValid, expected);
		lp.verifyIsLogedInSuccessfully(isValid, expected);
		
	}
	
	@Test(priority = 2, dependsOnMethods = "testLogin")
	public void verifyIslogoDisplayed() {
		LoginPage lp = new LoginPage(driver);
		lp.isLogoDisplayed();
	}
	

	@DataProvider(name="LoginDataProvider")
	public Object[][] loginDataProvider(){
		int ttlRows = ReadExcelData.getRowCount(fileName, "loginData");
		int ttlColumns = ReadExcelData.getColCount(fileName, "loginData");

		Object data[][] = new String[ttlRows-1][ttlColumns];

		for (int i = 1; i < ttlRows; i++) {

			for (int j = 0; j < ttlColumns; j++) {
				data[i-1][j]= ReadExcelData.getCellValue(fileName, "loginData", i, j);
			}

		}
		return data;
	}
}
