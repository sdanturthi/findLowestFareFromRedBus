package com.RedBus.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.RedBus.Utilities.ReadConfig;

public class BaseClass {
	WebDriver driver;
	ReadConfig rc = new ReadConfig();

	@BeforeClass
	public void SetUp() {
		String cdPath = System.getProperty("user.dir") + rc.getChromeDriverPath();
		System.out.println(cdPath);
		System.setProperty("webdriver.chrome.driver", cdPath);
		driver = new ChromeDriver();
	}

	@AfterClass
	public void TearDown() {
		driver.quit();
	}

}
