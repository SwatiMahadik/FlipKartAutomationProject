package com.flipkart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.base.TestBase;

public class LoginPage extends TestBase {

	@FindBy(xpath = "//img[@title='Flipkart']")
	WebElement logo;

	@FindBy(xpath = "//div[@class='_1XBjg- row']//form//input[@type='text']")
	WebElement mobileNum;

	@FindBy(xpath = "//div[@class='_1XBjg- row']//form//input[@type='password']")
	WebElement password;

	@FindBy(xpath = "//div[@class='_1XBjg- row']//form//button[@type='submit']")
	WebElement loginBtn;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	//login to flipkart
	public HomePage loginToFlipKart(String mob, String pass) throws InterruptedException {

		mobileNum.sendKeys(mob);
		System.out.println("mob entered");
		password.sendKeys(pass);
		System.out.println("password entered");
		loginBtn.click();
		System.out.println("Clicked on login button");
		return new HomePage();
	}
    //verify logo
	public boolean validateLogo() {
		return logo.isDisplayed();

	}

	//verify title
	public String getFlipKartTitle() {
		return driver.getTitle();
	}

}
