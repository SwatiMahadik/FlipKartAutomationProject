package com.flipkart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath="//input[@title='Search for products, brands and more']")
	WebElement searchProduct;

	@FindBy(xpath="//button[@type='submit']")
	WebElement searchIcon;
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String validateTitle()
	{
		return driver.getTitle();
	}
	
	public void searchProduct() throws InterruptedException
	{
		searchProduct.sendKeys("camera");
		searchIcon.click();
		Thread.sleep(5000);
	}
}
