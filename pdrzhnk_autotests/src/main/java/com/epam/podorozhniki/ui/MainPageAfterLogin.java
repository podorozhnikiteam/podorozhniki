package com.epam.podorozhniki.ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;

/**
 * Created by Zoja_Sharova on 7/25/2014.
 */

public class MainPageAfterLogin extends MethodsPage {

	protected WebDriver wdriver;
	private static Logger log = Logger.getLogger(MainPageAfterLogin.class);

	public MainPageAfterLogin(WebDriver driver) {
		super(driver);
		this.wdriver = driver;
		PageFactory.initElements(Driver.getInstance(), this);
	}

	@FindBy(xpath = "//a[@class='btn btn-default']")
	private WebElement logout;

	@FindBy(xpath = "//div[@class='col-lg-3']/span")
	private WebElement username;

	@FindBy(className = "errorblock")
	private WebElement error;

	@FindBy(id = "my_trips")
	private WebElement my_trips;

	
	public MyRoutesPage goToMyRoutesPage() {
		try {
			waitForElementFindBy(my_trips);
		} catch (NoSuchElementException e) {
			log.error("My_trips button was not found");
			e.printStackTrace();
		}
		my_trips.click();
		return new MyRoutesPage();
	}

}
