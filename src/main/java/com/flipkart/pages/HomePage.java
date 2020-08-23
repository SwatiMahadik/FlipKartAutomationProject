package com.flipkart.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//input[@title='Search for products, brands and more']")
	WebElement searchProduct;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement searchIcon;

	@FindBy(xpath = "//div[@class='bhgxx2 col-12-12'][5]")
	WebElement clickonProduct;

	@FindBy(xpath = "//button[@class='_2AkmmA _2Npkh4 _2MWPVK']")
	WebElement addToCart;

	@FindBy(xpath = "//a[@class='_3ko_Ud']")
	WebElement clickOnCart;

	@FindBy(xpath = "//button[@class='_2AkmmA iwYpF9 _7UHT_c']")
	WebElement placeOrder;

	@FindBy(xpath = "//button[@class='_2AkmmA _2Npkh4 _2kuvG8 _7UHT_c']")
	WebElement buyNow;

	@FindBy(xpath = "//div[@class='_3gijNv col-12-12']//h1")
	WebElement productName;

	@FindBy(xpath = "//div[@class='_1vC4OE _3qQ9m1']")
	WebElement price;

	@FindBy(xpath = "//div[@class='ooJZfD _2oZ8XT']//p[1]")
	WebElement productDesc;

	@FindBy(xpath = "//button[@class='_2AkmmA _I6-pD _7UHT_c']")
	WebElement deliverHerebtn;

	@FindBy(xpath = "//a[@class='_325-ji _3ROAwx']")
	WebElement productNameWhileCheckout;

	@FindBy(xpath = "//span[@class='pMSy0p XU9vZa']")
	WebElement productPriceWhileCheckout;

	@FindBy(xpath = "//div[@class='_2Y8lQ1']")
	WebElement addNewAddress;

	@FindBy(xpath = "//input[@name='name']")
	WebElement firstName;

	@FindBy(xpath = "//input[@name='phone']")
	WebElement mobileNumer;

	@FindBy(xpath = "//input[@name='pincode']")
	WebElement pinCode;

	@FindBy(xpath = "//input[@name='addressLine2']")
	WebElement locality;

	@FindBy(xpath = "//textarea[@name='addressLine1']")
	WebElement address;

	@FindBy(xpath = "//input[@type='radio']//preceding::span[contains(text(),'Home (All day delivery)')]")
	WebElement addressType;

	@FindBy(xpath = "//button[@class='_2AkmmA EqjTfe _7UHT_c']")
	WebElement saveAddressBtn;

	@FindBy(xpath = "//button[@class='FoDyGO']")
	WebElement editBtn;

	@FindBy(xpath = "//div[@class='_2aUbKa']")
	WebElement myAccountBtn;

	@FindBy(xpath = "//div[contains(text(),'Logout')]")
	WebElement logoutBtn;

	String ProductName;
	public String productPrice1;
	public String productPrice2;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String validateTitle() {
		return driver.getTitle();
	}

	public void searchProduct(String productname) throws InterruptedException {
		searchProduct.sendKeys(productname);
		searchIcon.click();
		clickonProduct.click();

		String parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();

		Iterator<String> I1 = s.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();

			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
				System.out.println(driver.switchTo().window(child_window));

				String productNames = productName.getText();
				String productName[] = productNames.split("\\(");
				String pname = productName[0];
				ProductName = pname.trim();
				System.out.println("Product Name while Search:" + pname);

				productPrice1 = price.getText();
				System.out.println("Product price while search:" + productPrice1);
				Thread.sleep(5000);

				if (addToCart.isDisplayed() == true) {
					addToCart.click();
				} else {
					System.out.println("Element is already in cart");
				}
				Thread.sleep(5000);
				driver.close();
			}

			driver.switchTo().window(parent);
		}
	}

	public void placeOrder() throws InterruptedException {
		clickOnCart.click();
		System.out.println("Clicked on cart");
		Thread.sleep(5000);

		List<WebElement> productlist = driver.findElements(By.xpath("//a[@class='_325-ji _3ROAwx']"));
		System.out.println("Number of elements:" + productlist.size());

		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			for (int i = 0; i < productlist.size(); i++) {

				if (ProductName.equals(productlist.get(i).getText())) {
					System.out.println("Product Name" + productlist.get(i).getText());
					productPrice2 = driver.findElement(By.xpath("//a[text()='" + ProductName + "']/following::span[@class='pMSy0p XU9vZa']"))
							.getText();
					System.out.println("Product Price while checkout:" + productPrice2);
					Thread.sleep(5000);
					js.executeScript("arguments[0].scrollIntoView(true);", productlist.get(i));
					Thread.sleep(3000);
					productlist.get(i).click();
					System.out.println("clicked on product");

					Thread.sleep(3000);
					break;

				}
			}
		} catch (Exception e) {

		}
		if (productPrice1.equals(productPrice1)) {
			System.out.println("Price Matched");
		}
		buyNow.click();
		System.out.println("Clicked on buy now button");
		if (editBtn.isDisplayed()) {
			deliverHerebtn.click();
			System.out.println("Clicked on deliver here button");
		} else {
			addNewAddress();

		}
	}

	public void addNewAddress() throws InterruptedException {
		addNewAddress.click();
		firstName.sendKeys("Swati11");
		mobileNumer.sendKeys("9857444444");
		pinCode.sendKeys("431003");
		locality.sendKeys("Cidco");
		address.sendKeys("plot no.13,N8");
		Thread.sleep(3000);
		addressType.click();
		saveAddressBtn.click();
		System.out.println("added new address");
	}

	public void logout() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(myAccountBtn).build().perform();
		Thread.sleep(3000);
		logoutBtn.click();
		Thread.sleep(3000);
		System.out.println("Logout successful");

	}
}