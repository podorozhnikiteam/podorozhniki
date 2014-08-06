package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import com.epam.podorozhniki.ui.MethodsPage;
import com.epam.podorozhniki.ui.MyTripsPage;

public class PassengerService extends MethodsPage {
	public PassengerService() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private MainPageAfterLogin mainPageAfterLogin;
	private MainPageBeforeLogin mainPageBeforeLogin;
	private MyTripsPage myTripsPage;
	private ReadingDatafile rd; 

	public int numPassPage;
	public int numPassBase;
	public String idtr;

	private By buttonRemove = By.xpath("//div[@id='PassengerTrips']//td[4]/a");
	private By nextPage = By.xpath("//div[@id='PassengerTrips']//li[3]/a");

	private static Logger log = Logger.getLogger(PassengerService.class);

	public void countTripsonPage(String passenger_username,
			String passenger_password) throws InterruptedException {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(passenger_username,
				passenger_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.gotoAsPassengerTab();
		Thread.sleep(3000);
		log.info("Passenger counts trips on the page");
		myTripsPage.countTripsOnPage();
		numPassPage = myTripsPage.countTrips(buttonRemove, nextPage);
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	public void countTripsonInDB(String query) throws InterruptedException,
			SQLException {
		log.info("Passenger counts trips in the DB");
		numPassBase = queryGetInt(query);
	}

	public void joinToTrip(String passenger_username,
			String passenger_password, String from_address, String to_address,
			String idtr) {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(passenger_username,
				passenger_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		log.info("Passenger joins to the specific trip");
		mainPageAfterLogin.joinTripByPassenger(from_address, to_address, idtr);
		mainPageAfterLogin.logout();
	}
	
	public void goToAsPassengerTab(String username, String password) {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(username,
				password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.gotoAsPassengerTab();
	}

}
