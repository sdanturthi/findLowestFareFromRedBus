package com.RedBus.TestCases;

import org.testng.annotations.Test;

import com.RedBus.PageObjects.HomePage;

public class HomePageTest extends BaseClass {

	@Test
	public void HomePageTest_TC001() throws Exception {
		String url = rc.getUrl();
		driver.get(url);
		driver.manage().window().maximize();
		HomePage hp = new HomePage(driver);

		String originCity = rc.getOriginCity();
		hp.sendOriginTB(originCity);

		String destinationCity = rc.getDestinationCity();
		hp.sendDestinationTB(destinationCity);

		hp.DatePickerClick();

		hp.getFare();

	}

}
