package com.epam.podorozhniki.us.US_1_1_2_8;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.db.*;
import com.epam.podorozhniki.core.*;
import com.epam.podorozhniki.ui.*;

public class TripRemovingDriverClass {
//	private WebDriver driver = Driver.getInstance();
	private MainPageAfterLogin mainPageAfterLogin;
	private static Logger log = Logger.getLogger(MainPageAfterLogin.class);

	@FindBy(xpath = ".//div[@id='routeResults']//tr[2]/td[1]")
	private WebElement from_point;

	@FindBy(xpath = ".//div[@id='routeResults']//tr[2]/td[2]")
	private WebElement to_point;

	private By details_Button = By.xpath("//a[contains(text(),'Details')]");
	private By nextPage = By.xpath("//div[@id='routeResults']//li[3]/a");

	public TripRemovingDriverClass() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	public int removingAsDriver() {
		driver.in
		mainPageAfterLogin = PageFactory.initElements(driver,
				MainPageAfterLogin.class);
		mainPageAfterLogin.countTripsOnTheMainPage(details_Button, nextPage);
		log.info("There are " + mainPageAfterLogin.numFromPage
				+ "on the AsDriverPage");
		return mainPageAfterLogin.numFromPage;

	}

}
