package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
	private DBService dbService;
	private MyTripsPage myTripsPage;
	private AddTripPage addTripPage;

	public String idtr;
	public String from_address;
	public String to_address;
	public String from_address_1;
	public String to_address_1;
	public String from_address_2;
	public String to_address_2;

	public int numDriverPage;
	public int numDriverBase;

	private static Logger log = Logger.getLogger(DriverService.class);

	public void addingTrips(String driver_username, String driver_password)
			throws InterruptedException {

		driver_username = System.getProperty("US1128.driver_login");
		driver_password = System.getProperty("US1128.driver_password");
		from_address = System.getProperty("US1128.from_adress");
		to_address = System.getProperty("US1128.to_adress");
		from_address_1 = System.getProperty("US1128.from_adress_1");
		to_address_1 = System.getProperty("US1128.to_adress_1");
		from_address_2 = System.getProperty("US1128.from_adress_2");
		to_address_2 = System.getProperty("US1128.to_adress_2");

		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		addTripPage = myTripsPage.gotoAddTripPage();
		myTripsPage = addTripPage.addTripZoja(from_address, to_address);
		idtr = myTripsPage.getTripId();
		System.out.println(idtr);
		addTripPage = myTripsPage.gotoAddTripPage();
		myTripsPage = addTripPage.addTripZoja(from_address_1, to_address_1);
		addTripPage = myTripsPage.gotoAddTripPage();
		myTripsPage = addTripPage.addTripZoja(from_address_2, to_address_2);
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
		myTripsPage.removeSpecificTrip(idtr_for_delete);
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
		myTripsPage.countTripsOnPage();
		numDriverPage = myTripsPage.countTripsOnPage();
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	public void countTripsinDB(String query) throws SQLException,
			InterruptedException {
		dbService = new DBService();
		dbService.countingTripsAsDriver(query);
		numDriverBase = dbService.numDriverBase;
	}

	public VerifyNumbersOfTrips goToVerifyTrips() {
		return new VerifyNumbersOfTrips();
	}

}
