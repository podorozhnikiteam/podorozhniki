package com.epam.podorozhniki.ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;

// was created for Zoja only for 1 day :)

public class MyTrips extends MethodsPage {
	private static Logger log = Logger.getLogger(MainPageAfterLogin.class);

	public MyTrips() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	@FindBy(xpath = ".//*[@id='li_driver']/a")
	private WebElement as_driver;

	public MyTrips goToDriverTrips() {
		try {
			waitForElementFindBy(as_driver);
		} catch (NoSuchElementException e) {
			log.error("as_driver button was not found");
			e.printStackTrace();
		}
		as_driver.click();
		return new MyTrips();
	}

}
