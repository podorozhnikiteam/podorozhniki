package com.epam.podorozhniki.us.US_1_1_2_8;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.AddTripPage;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import com.epam.podorozhniki.ui.MethodsPage;
import com.epam.podorozhniki.ui.MyTripsPage;

public class TripRemoving {

	private MainPageAfterLogin mainPageAfterLogin;
	private MainPageBeforeLogin mainPageBeforeLogin;
	private MyTripsPage myTripsPage;
	private AddTripPage addTripPage;

	public String from_address = "Киев, ул. Комарова, 12";
	public String to_address = "Киев, ул. Верхний Вал, 57";
	public String driver_username = "creditnew";
	public String driver_password = "1234567";
	public String idtr = "";

	protected int numFromPageBeforeDeleting;
	protected int numFromPageAfterDeleting;

	private static Logger log = Logger.getLogger(MainPageAfterLogin.class);

	@FindBy(xpath = ".//div[@id='routeResults']//tr[2]/td[1]")
	private WebElement from_point;

	@FindBy(xpath = ".//div[@id='routeResults']//tr[2]/td[2]")
	private WebElement to_point;

	private By details_Button = By.xpath("//a[contains(text(),'Details')]");
	private By nextPage = By.xpath("//div[@id='routeResults']//li[3]/a");

	public TripRemoving() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	// deleting trip as driver
	public void removingTripAsDriver() {
		log.info("removingTripAsDriver"); 
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.removePassengerTrip();
		log.info("trip was deleted");
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageBeforeLogin = mainPageAfterLogin.logout();
	}

 

}
