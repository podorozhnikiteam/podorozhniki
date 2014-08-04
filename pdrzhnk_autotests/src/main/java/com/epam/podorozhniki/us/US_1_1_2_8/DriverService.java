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
	public String from_address = "Киев, ул. Комарова, 12";
	public String to_address = "Киев, ул. Верхний Вал, 57";
	public String from_address_1 = "Киев, бул. Кольцова, 25";
	public String to_address_1 = "Киев, ул. Нижний Вал, 69";
	public String from_address_2 = "Киев, ул. Жилянская, 18";
	public String to_address_2 = "Киев, ул. Киприанова, 8";
	
	public int numDriverPage; 
	public int numDriverBase; 


	private By details_Button = By.xpath("//a[contains(text(),'Details')]");
	private By nextPageDriver = By.xpath("//div[@id='routeResults']//li[3]/a");
	private By nextPagePass = By.xpath("//div[@id='PassengerTrips']//li[3]/a");

	private static Logger log = Logger.getLogger(DriverService.class);

	public void addingTrips(String driver_username, String driver_password)
			throws InterruptedException {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		addTripPage = myTripsPage.gotoAddTripPage();
		myTripsPage = addTripPage.addTrip(from_address, to_address);
		idtr = myTripsPage.getTripId();
		System.out.println(idtr);
//		addTripPage = myTripsPage.gotoAddTripPage();
//		myTripsPage = addTripPage.addTrip(from_address_1, to_address_1);
//		addTripPage = myTripsPage.gotoAddTripPage();
//		myTripsPage = addTripPage.addTrip(from_address_2, to_address_2);
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	public void deletingTripAsDriver(String driver_username,
			String driver_password, String idtr) throws SQLException, InterruptedException {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.removeSpecificTrip(idtr);
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	public void countTripsOnPage(String driver_username,
			String driver_password) throws SQLException, InterruptedException {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.gotoAsDriverTab();
		//		 myTripsPage.countTripsOnThePage(details_Button, nextPageDriver);
//		 numDriverPage = myTripsPage.countTripsOnThePage(
//		 details_Button, nextPageDriver);
		Thread.sleep(3000);
		myTripsPage.countTripsInColume(1);
		numDriverPage = myTripsPage.countTripsInColume(1);
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	public void countTripsinDB(String query)
			throws SQLException, InterruptedException {
		dbService = new DBService();
		dbService.countingTripsAsDriver(query);
		numDriverBase = dbService.numDriverPage;
	}

	public void deletingDriverFromDB(String query) throws SQLException {
		dbService = new DBService();
		dbService.deletingTripsAsPassenger(query);

	}
	
	public VerifyNumbersOfTrips goToVerifyTrips(){
		return new VerifyNumbersOfTrips(); 
	}

}
