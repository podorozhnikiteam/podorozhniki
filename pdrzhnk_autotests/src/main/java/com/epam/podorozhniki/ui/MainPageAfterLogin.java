package com.epam.podorozhniki.ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;

/**
 * Created by Zoja_Sharova
 */

public class MainPageAfterLogin extends MethodsPage {

	public MainPageAfterLogin() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	@FindBy(xpath = "//div[@class='col-lg-3']/span")
	protected WebElement username;

	@FindBy(className = "errorblock")
	protected WebElement error;

	@FindBy(id = "my_trips")
	protected WebElement myTripsLink;

	@FindBy(xpath = "//ul[@class = 'button-list']/li[2]/a")
	protected WebElement logoutButton;

	@FindBy(id = "geoStart")
	protected WebElement fromAddressField;

	@FindBy(id = "geoFinish")
	protected WebElement toAddressField;

	@FindBy(id = "anydatecheck")
	protected WebElement anyDateCheckPoint;

	@FindBy(xpath = "//input[@value='Find route']")
	protected WebElement findTripButton;

	@FindBy(id = "save_btn")
	protected WebElement joinSeatsOkButton;

	@FindBy(xpath = "//tr[1]//button[contains(text(),'Join')]")
	protected WebElement joinTripButton;

	public MyTripsPage goToMyTripsPage() {
		myTripsLink.click();
		return new MyTripsPage();
	}

	public void joinTripByPassenger(String fromAddress, String toAddress,
			String idtr) {
		fromAddressField.clear();
		fromAddressField.sendKeys(fromAddress);
		toAddressField.clear();
		toAddressField.sendKeys(toAddress);
		anyDateCheckPoint.click();
		findTripButton.click();
		Driver.getInstance()
				.findElement(By.xpath("//button[@idtr='" + idtr + "']"))
				.click();
		joinSeatsOkButton.click();
		checkAlert("Successfully");
	}
}
