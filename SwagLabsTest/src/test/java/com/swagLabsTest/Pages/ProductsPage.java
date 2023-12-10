package com.swagLabsTest.Pages;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class ProductsPage extends Base{

	WebDriver driver;
	//constructor
	public ProductsPage(WebDriver lDriver) {
		this.driver=lDriver;
		PageFactory.initElements(driver, this);

	}

	//elements
	
	//list Elements of products
	@FindBy(css = ".inventory_item_name ")
	public static List<WebElement> productTitles;
	@FindBy(css = ".inventory_item_desc ")
	public static List<WebElement> productDecriptions;
	@FindBy(css = ".inventory_item_price ")
	public static List<WebElement> productPrices;

	@FindBy(xpath="/html/body/div/div/div/div[1]/div[2]/span")
	public static WebElement PageTitle;
	@FindBy(xpath="//*[@id=\"item_4_title_link\"]/div")
	public static WebElement itemTitle;
	@FindBy(xpath="/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[1]/div")
	public static WebElement itemDescription;
	@FindBy(xpath="/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[2]/div")
	public static WebElement itemPrice;
	@FindBy(xpath="/html/body/div/div/div/div[2]/div/div/div/div[1]/div[1]/a/img")
	public static WebElement itemImage;
	@FindBy(xpath="/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[2]/button")
	public static WebElement addToCartBtn;
	@FindBy(xpath="/html/body/div/div/div/div[1]/div[2]/div/span/select")
	public static WebElement itemFilter;
	@FindBy(xpath="/html/body/div/div/div/div[1]/div[2]/div/span/select/option[1]")
	public static WebElement AtoZ;
	@FindBy(xpath="/html/body/div/div/div/div[1]/div[2]/div/span/select/option[2]")
	public static WebElement ZtoA;
	@FindBy(xpath="/html/body/div/div/div/div[1]/div[2]/div/span/select/option[3]")
	public static WebElement priceLowtoHigh;
	@FindBy(xpath="/html/body/div/div/div/div[1]/div[2]/div/span/select/option[4]")
	public static WebElement priceHightoLow;
	@FindBy(xpath="/html/body/div/div/div/div[1]/div[1]/div[3]/a/span")
	public static WebElement cartCount;
	@FindBy(id="back-to-products")
	public static WebElement backToProducts;


	public void verifyTitle(Object title) throws IOException {

		WebElement foundTitle = null;
		String actualTitle = null;

		for (WebElement productTitle : productTitles) {
			actualTitle = productTitle.getText();

			if (actualTitle.equalsIgnoreCase((String)title)) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].scrollIntoView(true);", productTitle);
				foundTitle = productTitle;
				break;
			}
		}

		if (foundTitle == null) {
			CaptureScreenshot(driver, "verifyProductDisplayingWithAllDetails");
			// Assert that the actual title matches the expected title
			Assert.fail("product title [" + title + "] not Maching.");
		}
		
//		String actualTitle = itemTitle.getText();
//		if (actualTitle.equals(title)) {
//
//			Assert.assertEquals(actualTitle, title);
//		}
//		else {
//
//			CaptureScreenshot(driver, "verifyProductTitle");
//			Assert.assertEquals(actualTitle, title);
//		}
	}

	public void verifyDescription(Object description) throws IOException {

			WebElement foundDescription = null;
			String actualDesc = null;

			for (WebElement Productdescription : productDecriptions) {
				actualDesc = Productdescription.getText();

				if (actualDesc.equalsIgnoreCase((String)description)) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
		            js.executeScript("arguments[0].scrollIntoView(true);", Productdescription);
					foundDescription = Productdescription;
					break;
				}
			}

			if (foundDescription == null) {
				CaptureScreenshot(driver, "verifyProductDisplayingWithAllDetails");
				// Assert that the actual title matches the expected title
				Assert.fail("product description [" + description + "] not Maching.");
			}
	}

	public void verifyPrice(Object price) throws IOException {
		WebElement foundPrice = null;
		String actualPrice = null;

		for (WebElement productPrice : productPrices) {
			actualPrice = productPrice.getText();

			if (actualPrice.equalsIgnoreCase((String)price)) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].scrollIntoView(true);", productPrice);
				foundPrice = productPrice;
				break;
			}
		}

		if (foundPrice == null) {
			CaptureScreenshot(driver, "verifyProductDisplayingWithAllDetails");
			// Assert that the actual title matches the expected title
			Assert.fail("product price [" + price + "] not Maching.");
		}
	}


}
