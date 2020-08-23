package com.flipkart.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flipkart.base.TestBase;
import com.flipkart.pages.HomePage;
import com.flipkart.pages.LoginPage;

public class HomePageTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	String sheetname = "AddressDetails";

	public HomePageTest() 
	{
		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException 
	{
		intialization();
		loginpage = new LoginPage();
		homepage = loginpage.loginToFlipKart(prop.getProperty("mobile"), prop.getProperty("password"));
		System.out.println("Login Successful");
		Thread.sleep(5000);
	}

	@Test(priority = 1, enabled = true)
	public void verifyHomePageTitleTest() 
	{
		String homepagetitle = homepage.validateTitle();
		Assert.assertEquals(homepagetitle,"Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!",
				"Login Successful");
		System.out.println("Title is :" + homepagetitle);
	}

	@Test(priority = 2, enabled = true)
	public void verifySearchProductTest() throws InterruptedException
	{
		homepage.searchProduct(prop.getProperty("productName"));
		homepage.placeOrder();

	}

	@Test(priority = 3, enabled = true)
	public void verifyLogoutTest() throws InterruptedException {
		homepage.logout();
	}

	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
