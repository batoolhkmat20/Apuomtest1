package test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class test {

	DesiredCapabilities caps = new DesiredCapabilities();

	AndroidDriver driver;

	@BeforeTest
	public void myBeofreTesting() {
		// we alway use those two lines the only thing you need to change is the device
		// name
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "batt");

		// we use those two lines only when we need to test the browser on the emulator
		/*
		 * caps.setCapability("chromedriverExecutable", "D:\\chrome\\chromedriver.exe");
		 * caps.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
		 * 
		 */
		// for mobile application we use the below two lines
		File myApplicationCalculator = new File("src/App/calculator.apk");
		caps.setCapability(MobileCapabilityType.APP, myApplicationCalculator.getAbsolutePath());

	}

	@Test(enabled = false)
	public void testWebsiteOnMobileBrowser() throws MalformedURLException {

		// always with browser testing or with mobile testing
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();

		List<WebElement> myAddToCartButtons = driver.findElements(By.className("btn"));

		for (int i = 0; i < myAddToCartButtons.size(); i++) {
			myAddToCartButtons.get(i).click();
		}
	}

	@Test()
	public void myAppTesting() throws MalformedURLException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();

		driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_4")).click();
		String Result = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		System.out.println(Result);
	}

	@AfterTest
	public void myPostTesting() {
	}

}