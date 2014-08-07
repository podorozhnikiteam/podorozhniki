package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
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
		myTripsPage.catchAlert();
		idtr = myTripsPage.getTripId();
//		addTripPage = myTripsPage.gotoAddTripPage();
//		myTripsPage = addTripPage.addTrip(rd.from_address_1, rd.to_address_1,
//				rd.alert);
//		myTripsPage.catchAlert();
//		addTripPage = myTripsPage.gotoAddTripPage();
//		myTripsPage = addTripPage.addTrip(rd.from_address_2, rd.to_address_2,
//				rd.alert);
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

	// user counts trips on page depends on user's role
	public void countTripsOnPage(String username, String password,
			By button_for_list, By nextPage, WebElement user_role)
			throws SQLException, InterruptedException {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(username, password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.gotoRoleTab(user_role);
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

	// go to specific tab depends on user"s role
	public void goToUserTab(String username, String password,
			WebElement user_role) {
		log.info(" on  the goToUserTab mathod");
		mainPageBeforeLogin = new MainPageBeforeLogin();
		log.info(" enter loginpassword");
		mainPageBeforeLogin.enterLoginAndPass(username, password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		log.info(" goToMyTripsPage");
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		log.info(" gotoRoleTab");
		myTripsPage.gotoRoleTab(user_role);
		log.info("gotoRoleTab was succesful "); 
	}

	public void goToPassStatus(String username, String password, String idtr,
			String pass_status) {
		log.info("Passenger set status");
		myTripsPage.setStatusToPassengerTrip(pass_status);
		log.info("DRIVER OPEN MAIN PAGE.");
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		log.info("DRIVER LOGOUT FROM THE SYSTEM.");
		mainPageAfterLogin.logout();
	}

}
