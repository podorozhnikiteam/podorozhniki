package com.epam.podorozhniki.us.US_1_1_2_8;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.AddTripPage;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import com.epam.podorozhniki.ui.MyTripsPage;


public class RunnerUS_1128 {
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

	@Test 
	public void Runner() {
		
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
