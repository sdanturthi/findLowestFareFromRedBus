package com.RedBus.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.RedBus.Utilities.ReadConfig;

public class HomePage {

	WebDriver ldriver;

	String datePickerMonth;
	String datePickerYear;

	public HomePage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//div/input[@id='src']")
	WebElement origin_TextBox;

	public void sendOriginTB(String originCity) {
		origin_TextBox.sendKeys(originCity);
		CityDropDownSelect();
	}

	@FindBy(xpath = "//div/input[@id='dest']")
	WebElement destinationCity_TextBox;

	public void sendDestinationTB(String destinationCity) {
		destinationCity_TextBox.sendKeys(destinationCity);
		CityDropDownSelect();
	}

	@FindBy(xpath = "//ul[@class='autoFill homeSearch']/li[@class='selected']")
	WebElement cityDropDownSelection;

	public void CityDropDownSelect() {
		WebDriverWait wait = new WebDriverWait(ldriver, 10);
		WebElement cityDropDown = wait.until(ExpectedConditions.elementToBeClickable(cityDropDownSelection));
		cityDropDown.click();
	}

	@FindBy(xpath = "//div/input[@id='onward_cal']")
	WebElement DatePicker;

	@FindBy(xpath = "//table/tbody/tr/td[@class='next']/button")
	WebElement nextButton;

	public void DatePickerClick() {

		DatePicker.click();
		ReadConfig rc = new ReadConfig();
		String travelDate = rc.getTravelDate();
		String[] dateElements = travelDate.split("-");
		String userDate = dateElements[0];
		String userMonth = dateElements[1];
		String userYear = dateElements[2];

		getMonthTitle();

		while (true) {

			if (!datePickerYear.equals(userYear)) {
				ldriver.findElement(By.xpath("//table/tbody/tr/td[@class='next']/button")).click();
				getMonthTitle();
			}

			else if (!userMonth.contains(datePickerMonth)) {
				ldriver.findElement(By.xpath("//table/tbody/tr/td[@class='next']/button")).click();
				getMonthTitle();
			}

			if (datePickerYear.equals(userYear) && userMonth.contains(datePickerMonth))
				break;

		}

		ldriver.findElement(By.xpath("//table/tbody/tr/td[contains(text()," + "'" + userDate + "'" + ")]")).click();

		ldriver.findElement(By.xpath("//div/button[@id='search_btn']")).click();
	}

	@FindBy(xpath = "//div[@class='w-15 fl f-12 d-color']/a[contains(text(), 'Fare')]")
	WebElement FareColumn;

	@FindBy(xpath = "//div[@class='clearfix row-one']/div[@class='column-seven p-right-10 w-15 fl']/div[@class='seat-fare ']/div[@class='fare d-block']/span")
	WebElement fareFinder;

	public void getFare() {

		WebDriverWait wait = new WebDriverWait(ldriver, 100);
		WebElement fareColumnElement = wait.until(ExpectedConditions.elementToBeClickable(FareColumn));
		fareColumnElement.click();
		String fare = fareFinder.getText();
		System.out.println("Lowest Fare: " + fare);
	}

	@FindBy(xpath = "//tr/td[@class='monthTitle']")
	WebElement monthTitle;

	public void getMonthTitle() {
		String monthYearTitle = monthTitle.getText();
		String[] monthYearElements = monthYearTitle.split(" ");
		datePickerMonth = monthYearElements[0];
		datePickerYear = monthYearElements[1];
	}

}