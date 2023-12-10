package com.swagLabsTest.testcases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.swagLabsTest.Pages.Base;
import com.swagLabsTest.Pages.LoginPage;
import com.swagLabsTest.Pages.ProductsPage;
import com.swagLabsTest.utility.ReadExcelData;

public class testProduct extends Base{
	
	String fileName=System.getProperty("user.dir")+"\\TestData\\testData.xlsx";
	
	@Test(priority = 1)
	public void login() {
		LoginPage lp = new LoginPage(driver);
		Object username=ReadExcelData.getCellValue(fileName, "loginData", 6, 0);
		Object password=ReadExcelData.getCellValue(fileName, "loginData", 6, 1);
		lp.login(username, password);
	}

	@Test(priority = 2, dataProvider = "productDataProvider")
	public void verifyProductDisplayingWithAllDetails(String title, String description, String price) throws IOException {
		
		ProductsPage pp= new ProductsPage(driver);
		pp.verifyTitle(title);
		pp.verifyDescription(description);
		pp.verifyPrice(price);
		
	}

//	@Test(priority = 4)
//	public void verifyProductPrice() throws IOException {
//		
//		ProductsPage pp= new ProductsPage(driver);
//
//		Object price=ReadExcelData.getCellValue(fileName, "products", 1, 2);
//		pp.verifyPrice(price);
//		
//	}
	

	@DataProvider(name="productDataProvider")
	public Object[][] ProductDataProvider(){
		int ttlRows = ReadExcelData.getRowCount(fileName, "products");
		int ttlColumns = ReadExcelData.getColCount(fileName, "products");
		Object data[][] = new String[ttlRows-1][ttlColumns];

		for (int i = 1; i < ttlRows; i++) {

			for (int j = 0; j < ttlColumns; j++) {
				data[i-1][j]= ReadExcelData.getCellValue(fileName, "products", i, j);
			}

		}
		return data;
	}
}
