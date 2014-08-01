package com.epam.podorozhniki.us.US_1_1_2_8;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import com.epam.podorozhniki.ui.MethodsPage;
import com.epam.podorozhniki.ui.MyTripsPage;

public class CountTripOnPage {
	private MainPageAfterLogin mainPageAfterLogin;
	private MainPageBeforeLogin mainPageBeforeLogin;

	private MyTripsPage myTripsPage;

	public String from_address = "Киев, ул. Комарова, 12";
	public String to_address = "Киев, ул. Верхний Вал, 57";
	public String driver_username = "creditnew";
	public String driver_password = "1234567";
	public String passenger_username = "creditnew";
	public String passenger_password = "1234567";
	public String idtr = "";

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;
	protected int numFromPageAsPassengerBeforeDelet;
	protected int numFromPageAsPassengerAfterDelet;

	private static Logger log = Logger.getLogger(MainPageAfterLogin.class);

	private By details_Button = By.xpath("//a[contains(text(),'Details')]");
	private By nextPage = By.xpath("//div[@id='routeResults']//li[3]/a");

	public CountTripOnPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	@FindBy(xpath = "//li[@id='li_driver']/a")
	private WebElement asDriverTab;

	@FindBy(xpath = "//li[@id='li_passenger']/a")
	protected WebElement asPassengerTab;

	// count trips before deleting as driver
	public void countingTripsBeforeDeletingAsDriver() {
		log.info("count trip on the driver page before deleting");
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		// myTripsPage.goToAsDriverOrPassengerTab(asDriverTab);
		myTripsPage.gotoAsDriverTab();
		myTripsPage.countTripsOnThePage(details_Button, nextPage);
		MethodsPage.numFromPage = numFromPageAsDriverBeforeDelet;
		log.info("There are " + numFromPageAsDriverBeforeDelet
				+ "on the AsDriverPage before deleting");
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	// count trips before deleting as passenger
	public void countingTripsBeforeDeletingAsPassenger() {
		log.info("count trip on the passenger page before deleting");
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(passenger_username,
				passenger_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		// myTripsPage.goToAsDriverOrPassengerTab(asPassengerTab);
		myTripsPage.gotoAsPassengerTab();
		myTripsPage.countTripsOnThePage(details_Button, nextPage);
		MethodsPage.numFromPage = numFromPageAsPassengerBeforeDelet;
		log.info("There are " + numFromPageAsPassengerBeforeDelet
				+ "on the AsPassehgerPage before deleting");
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	// count trips after deleting as driver
	public void countingTripsAsDriverAFterDeleting() {
		log.info("count trip on the driver page after deleting");
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		// myTripsPage.goToAsDriverOrPassengerTab(asDriverTab);
		myTripsPage.gotoAsDriverTab();
		myTripsPage.countTripsOnThePage(details_Button, nextPage);
		MethodsPage.numFromPage = numFromPageAsDriverAfterDelet;
		log.info("There are " + numFromPageAsDriverAfterDelet
				+ "on the AsDriverPage after deleting");
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageBeforeLogin = mainPageAfterLogin.logout();
	}

	// count trips after deleting as passenger
	public void countingTripsAsPassengerAFterDeleting() {
		log.info("count trip on the passenger page after deleting");
		mainPageBeforeLogin.enterLoginAndPass(passenger_username,
				passenger_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		// myTripsPage.goToAsDriverOrPassengerTab(asPassengerTab);
		myTripsPage.gotoAsPassengerTab();
		myTripsPage.countTripsOnThePage(details_Button, nextPage);
		MethodsPage.numFromPage = numFromPageAsPassengerAfterDelet;
		log.info("There are " + numFromPageAsPassengerAfterDelet
				+ "on the AsPassehgerPage after deleting");
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageBeforeLogin = mainPageAfterLogin.logout();
	}

	public TripRemoving goToTripRemoving() {
		return new TripRemoving();
	}

	public VerifyNumbersOfTrips goToVerifyNumbersOfTripsPage() {
		return new VerifyNumbersOfTrips();
	}

	public CountTripsInDatabase goToDB() {
		return new CountTripsInDatabase();
	}
}
