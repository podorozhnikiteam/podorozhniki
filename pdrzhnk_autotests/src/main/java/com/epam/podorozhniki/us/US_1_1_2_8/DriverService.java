package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.AddTripPage;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import com.epam.podorozhniki.ui.MethodsPage;
import com.epam.podorozhniki.ui.MyTripsPage;

public class DriverService extends MethodsPage {
	public DriverService() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private MainPageAfterLogin mainPageAfterLogin;
	private MainPageBeforeLogin mainPageBeforeLogin;
	private MyTripsPage myTripsPage;
	private AddTripPage addTripPage;
	private ReadingDatafile rd;

	public String idtr;

	public int numDriverPage;
	public int numDriverBase;

	private By buttonDetails = By.xpath("//a[contains(text(),'Details')]");
	private By nextPage = By.xpath("//div[@id='routeResults']//li[3]/a");

	private static Logger log = Logger.getLogger(DriverService.class);

	public void addingTrips(String driver_username, String driver_password)
			throws InterruptedException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		log.info("Driver service statrted");
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		addTripPage = myTripsPage.gotoAddTripPage();
		log.info("Driver adds the trips");
		myTripsPage = addTripPage.addTrip(rd.from_address, rd.to_address);
		idtr = myTripsPage.getTripId();
		addTripPage = myTripsPage.gotoAddTripPage();
		myTripsPage = addTripPage.addTrip(rd.from_address_1, rd.to_address_1);
		addTripPage = myTripsPage.gotoAddTripPage();
		myTripsPage = addTripPage.addTrip(rd.from_address_2, rd.to_address_2);
		myTripsPage.catchAlert();
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	public void deletingTripAsDriver(String driver_username,
			String driver_password, String idtr_for_delete)
			throws SQLException, InterruptedException {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		log.info("Drives removes specific trip");
		myTripsPage.removeSpecificTrip(idtr_for_delete);
		myTripsPage.catchAlert();
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	public void countTripsOnPage(String driver_username, String driver_password)
			throws SQLException, InterruptedException {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.gotoAsDriverTab();
		Thread.sleep(3000);
		log.info("Driver counts trips on the page");
		numDriverPage = myTripsPage.countTrips(buttonDetails, nextPage);
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	public void goToAsDRiverTab(String username, String password) {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(username,
				password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.gotoAsDriverTab();
	}

	public void countTripsinDB(String query) throws SQLException,
			InterruptedException {
		log.info("Driver counts trips in the DB");
		numDriverBase = queryGetInt(query);

	}

}
