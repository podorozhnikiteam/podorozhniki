package com.epam.podorozhniki.us.US_1_1_2_8;

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
import com.epam.podorozhniki.ui.MyTripsPage;

public class TripRemovingAsDriver {

	private MainPageAfterLogin mainPageAfterLogin;
	private MainPageBeforeLogin mainPageBeforeLogin;
	private MyTripsPage myTripsPage;
	private AddTripPage addTripPage;

	public String from_address = "Киев, ул. Жилянская, 12";
	public String to_address = "Киев, ул. Верхний Вал, 14";
	public String driver_username = "creditnew";
	public String driver_password = "1234567";
	public String idtr = "";

	private static Logger log = Logger.getLogger(MainPageAfterLogin.class);

	@FindBy(xpath = ".//div[@id='routeResults']//tr[2]/td[1]")
	private WebElement from_point;

	@FindBy(xpath = ".//div[@id='routeResults']//tr[2]/td[2]")
	private WebElement to_point;

	private By details_Button = By.xpath("//a[contains(text(),'Details')]");
	private By nextPage = By.xpath("//div[@id='routeResults']//li[3]/a");

	public TripRemovingAsDriver() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	@Before
	public void setUp() throws InterruptedException {
		Driver.init();
		Driver.getInstance().manage().window().maximize();
		Driver.getInstance().get(
				"http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main");
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		addTripPage = myTripsPage.gotoAddTripPage();
		myTripsPage = addTripPage.addTrip(from_address, to_address);
		idtr = myTripsPage.getTripId();
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageBeforeLogin = mainPageAfterLogin.logout();
	}

	// count trips before deleting
	@Test
	public void countTripAsDriver() {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.countTripsOnThePage(details_Button, nextPage);
		log.info("There are " + mainPageAfterLogin.numFromPage
				+ "on the AsDriverPage");
		mainPageAfterLogin = PageFactory.initElements(Driver.getInstance(),
				MainPageAfterLogin.class);
		// mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	//deleting trip as driver 
	@Test
	public void removingTripAsDriver() {
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.removePassengerTrip();
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageBeforeLogin = mainPageAfterLogin.logout();
	}
// check number of trips after deleting
	@Test
	public void verifyNumberOfTrips() {
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.countTripsOnThePage(details_Button, nextPage); 
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageBeforeLogin = mainPageAfterLogin.logout();
	}

	@After
	public void afterTest() {
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.removePassengerTrip();
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageBeforeLogin = mainPageAfterLogin.logout();
		Driver.tearDown();
	}

}
