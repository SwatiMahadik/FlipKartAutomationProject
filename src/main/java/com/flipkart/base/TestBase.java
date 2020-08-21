package com.flipkart.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.flipkart.util.TestUtil;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public TestBase(){
		
		try{
			prop=new Properties();
			FileInputStream fp=new FileInputStream("C:/Users/Sai/workspace/FlipKartAutomationTest/src/main/java/com/flipkart/config/config.properties");
			prop.load(fp);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
      }

	public static void intialization()
	{
		String browsername=prop.getProperty("browser");
		if(browsername.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","D:\\Swati\\chromedriver_win32 (1)\\chromedriver.exe");
			 driver = new ChromeDriver();

		}
		else if(browsername.equals("firefox"))
	    {
			driver = new FirefoxDriver();
	    }
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
	}
	
}


