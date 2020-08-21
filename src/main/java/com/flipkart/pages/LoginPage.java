package com.flipkart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.base.TestBase;
import com.flipkart.util.TestUtil;

public class LoginPage extends TestBase{

	@FindBy(xpath="//a[@class='_3Ep39l']")
	WebElement loginLink;

	@FindBy(xpath="//div[@class='_1XBjg- row']//form//input[@type='text']")
	WebElement mobileNum;
	
	@FindBy(xpath="//div[@class='_1XBjg- row']//form//input[@type='password']")
	WebElement password;
	
	@FindBy(xpath="//div[@class='_1XBjg- row']//form//button[@type='submit']")
	WebElement loginBtn;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public HomePage loginToFlipKart(String mob,String pass) throws InterruptedException {
			
		mobileNum.sendKeys(mob);
		Thread.sleep(200);
		System.out.println("mob entered");
		password.sendKeys(pass);
		Thread.sleep(200);
		System.out.println("password entered");
		loginBtn.click();
		System.out.println("Clicked on login button");
		return new HomePage();
	}
	
	public String getFlipKartTitle()
	{
		return driver.getTitle();
	}

}
