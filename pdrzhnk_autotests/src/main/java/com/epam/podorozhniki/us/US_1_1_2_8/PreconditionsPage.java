package com.epam.podorozhniki.us.US_1_1_2_8;

import org.apache.log4j.Logger;

import com.epam.podorozhniki.ui.*;
import com.epam.podorozhniki.core.*;
import com.epam.podorozhniki.db.*;
import com.epam.podorozhniki.us.*;

public class PreconditionsPage extends MethodsPage {
	private MainPageAfterLogin mainPageAfterLogin;
	private MainPageBeforeLogin mainPageBeforeLogin;
	private MyTripsPage myTripsPage;
	private AddTripPage addTripPage;

	public String from_address = "Киев, ул. Комарова, 12";
	public String to_address = "Киев, ул. Верхний Вал, 57";
	public String from_address_1 = "Киев, бул. Кольцова, 25";
	public String to_address_1 = "Киев, ул. Нижний Вал, 69";
	public String from_address_2 = "Киев, ул. Жилянская, 18";
	public String to_address_2 = "Киев, ул. Киприанова, 8";
	public String driver_username = "creditnew";
	public String driver_password = "1234567";
	public String passenger_username = "creditnew2";
	public String passenger_password = "1234567";
	public String idtr = "";

	protected int numFromPageBeforeDeleting;
	protected int numFromPageAfterDeleting;

	private static Logger log = Logger.getLogger(PreconditionsPage.class);

	public void addingTripsAsDriver() throws InterruptedException {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		addTripPage = myTripsPage.gotoAddTripPage();
		myTripsPage = addTripPage.addTrip(from_address, to_address);
		myTripsPage = addTripPage.addTrip(from_address_1, to_address_2);
		myTripsPage = addTripPage.addTrip(from_address_1, to_address_2);
		idtr = myTripsPage.getTripId();
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageBeforeLogin = mainPageAfterLogin.logout();
	}

	public void addingToTripAsPassenger() {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(passenger_username,
				passenger_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		mainPageAfterLogin.joinTripByPassenger(from_address, to_address, idtr);
		mainPageAfterLogin.logout();
	}
	
	public MainPageAfterLogin goToMainPageAfterLogin() { 
		return new MainPageAfterLogin(); 
	}
}
