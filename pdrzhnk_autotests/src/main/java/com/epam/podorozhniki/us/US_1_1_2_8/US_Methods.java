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

/*
 * Created by Zoja_Sharova
 */

public class US_Methods extends MethodsPage {
	public US_Methods() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private MainPageAfterLogin mainPageAfterLogin;
	private MainPageBeforeLogin mainPageBeforeLogin;
	private MyTripsPage myTripsPage;
	private AddTripPage addTripPage;
	private ReadingDatafile rd;

	public String idtr;

	public int numPage;

	private static Logger log = Logger.getLogger(US_Methods.class);

	// driver adds 3 trips
	public void addingTrips(String username, String password)
			throws InterruptedException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		log.info("Driver service statrted");
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(username, password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		addTripPage = myTripsPage.gotoAddTripPage();
		log.info("Driver adds the trips");
		myTripsPage = addTripPage.addTrip(rd.from_address, rd.to_address,
				rd.alert);
		idtr = myTripsPage.getTripId();
		addTripPage = myTripsPage.gotoAddTripPage();
		myTripsPage = addTripPage.addTrip(rd.from_address_1, rd.to_address_1,
				rd.alert);
		addTripPage = myTripsPage.gotoAddTripPage();
		myTripsPage = addTripPage.addTrip(rd.from_address_2, rd.to_address_2,
				rd.alert);
		myTripsPage.catchAlert();
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	// driver deletes specific trip
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

	// user counts trips on page
	public void countTripsOnPage(String username, String password,
			By button_for_list, By nextPage) throws SQLException,
			InterruptedException {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(username, password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.gotoAsDriverTab();
		Thread.sleep(3000);
		log.info("User counts trips on the page");
		numPage = myTripsPage.countTrips(button_for_list, nextPage);
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	// passenger joins to the specific trip
	public void joinToTrip(String username, String password,
			String from_address, String to_address, String idtr) {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(username, password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		log.info("Passenger joins to the specific trip");
		mainPageAfterLogin.joinTripByPassenger(from_address, to_address, idtr);
		mainPageAfterLogin.logout();
	}

	public void goToAsDRiverTab(String username, String password) {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(username, password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.gotoAsDriverTab();
	}

	public void goToAsPassengerTab(String username, String password) {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(username, password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.gotoAsPassengerTab();
	}
	
	public void goToSubmittedStatus (String username, String password, String idtr) {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(username,
				password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		log.info("DRIVER OPEN 'My Trips' TAB.");
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		log.info("DRIVER ACCEPT PASSENGER'S TRIP.");
		myTripsPage.acceptPassengerTrip(idtr);
		log.info("DRIVER OPEN MAIN PAGE.");
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		log.info("DRIVER LOGOUT FROM THE SYSTEM.");
		mainPageBeforeLogin = mainPageAfterLogin.logout();
		
	}
	

}
