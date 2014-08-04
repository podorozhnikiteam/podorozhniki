package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.AddTripPage;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import com.epam.podorozhniki.ui.MethodsPage;
import com.epam.podorozhniki.ui.MyTripsPage;

public class TC_1128_1 extends MethodsPage {
	
	public TC_1128_1() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private DriverService driverService;
	private PassengerService pasService;
	private VerifyNumbersOfTrips numbersOfTrips;

	public String driver_username;
	public String driver_password;
	public String passenger_username;
	public String passenger_password;

	public String idtr;
	public String queryDeletingAllDriverTrips;
	public String queryAsDriver;
	public String queryAsPassenger;
	public String queryDeletingAllPassengerTrips;

	public String from_address = "Киев, ул. Комарова, 12";
	public String to_address = "Киев, ул. Верхний Вал, 57";
	public String from_address_1 = "Киев, бул. Кольцова, 25";
	public String to_address_1 = "Киев, ул. Нижний Вал, 69";
	public String from_address_2 = "Киев, ул. Жилянская, 18";
	public String to_address_2 = "Киев, ул. Киприанова, 8";

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;
	protected int numFromBaseAsDriverBeforeDelet;
	protected int numFromBaseAsDriverAfterDelet;
	protected int numFromPageAsPassBeforeDelet;
	protected int numFromPageAsPassAfterDelet;
	protected int numFromBaseAsPassBeforeDelet;
	protected int numFromBaseAsPassAfterDelet;

	private By details_Button = By.xpath("//a[contains(text(),'Details')]");
	private By nextPage = By.xpath("//div[@id='routeResults']//li[3]/a");

	private static Logger log = Logger.getLogger(TC_1128_1.class);

	@Before
	public void setUp() {
		Driver.init();
		driver_username = System.getProperty("US1128.driver_login");
		driver_password = System.getProperty("US1128.driver_password");
		passenger_username = System.getProperty("US1128.passenger_login");
		passenger_password = System.getProperty("US1128.passenger_password");
		queryDeletingAllDriverTrips = System
				.getProperty("US1128.queryDeletingAllDriverTrips");
		queryAsDriver = System.getProperty("US1128.queryAsDriver");
		queryAsPassenger = System.getProperty("US1128.queryAsPassenger");
		queryDeletingAllPassengerTrips = System
				.getProperty("US1128.queryDeletingAllPassengerTrips");
		Driver.getInstance().manage().window().maximize();
		Driver.getInstance().get(
				"http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main");
	}

	// @Test
	// public void removingTripAsDriverOnly() throws InterruptedException,
	// SQLException {
	// driverService = new DriverService();
	// driverService.addingTrips(driver_username, driver_password);
	// driverService.countTripsOnPage(driver_username, driver_password);
	// numFromPageAsDriverBeforeDelet = driverService.numDriverPage;
	// System.out.println("Driver has " + numFromPageAsDriverBeforeDelet
	// + " trips on the page before deleting");
	// driverService.countTripsinDB(queryAsDriver);
	// numFromBaseAsDriverBeforeDelet = driverService.numDriverBase;
	// System.out.println("Driver has " + numFromBaseAsDriverBeforeDelet
	// + " trips in the DB before deleting");
	// driverService.deletingTripAsDriver(driver_username, driver_password);
	// driverService.countTripsOnPage(driver_username, driver_password);
	// numFromPageAsDriverAfterDelet = driverService.numDriverPage;
	// System.out.println("Driver has " + numFromPageAsDriverAfterDelet
	// + " trips on the page after deleting");
	// driverService.countTripsinDB(queryAsDriver);
	// numFromBaseAsDriverAfterDelet = driverService.numDriverBase;
	// System.out.println("Driver has " + numFromBaseAsDriverAfterDelet
	// + " trips in the DB after deleting");
	// numbersOfTrips = driverService.goToVerifyTrips();
	// numbersOfTrips.VerifyNumberOfTripsOnthePageAsDriver(numFromPageAsDriverBeforeDelet,
	// numFromPageAsDriverAfterDelet);
	// numbersOfTrips.VerifyNumberOfTripsInTheDBAsDriver(numFromBaseAsDriverBeforeDelet,
	// numFromBaseAsDriverAfterDelet);
	// driverService = new DriverService();
	// driverService.deletingDriverFromDB(queryDeletingAllDriverTrips);
	// }

	@Test
	public void removingTripWithPassenger() throws InterruptedException,
			SQLException {
		driverService = new DriverService();
		driverService.addingTrips(driver_username, driver_password);
		driverService.countTripsOnPage(driver_username, driver_password);
		numFromPageAsDriverBeforeDelet = driverService.numDriverPage;
		idtr  = driverService.idtr; 
		System.out.println("Driver has " + numFromPageAsDriverBeforeDelet
				+ " trips on the page before deleting, idtr = "+idtr);
		driverService.countTripsinDB(queryAsDriver);
		numFromBaseAsDriverBeforeDelet = driverService.numDriverBase;
		System.out.println("Driver has " + numFromBaseAsDriverBeforeDelet
				+ " trips in the DB before deleting");
		pasService = new PassengerService();
		pasService.joinToTrip(passenger_username, passenger_password, from_address, to_address,idtr );
		pasService.countTripsonPage(passenger_username, passenger_password);
		numFromBaseAsPassBeforeDelet = pasService.numPassPage;
		System.out.println("Passenger has " + numFromPageAsPassBeforeDelet
				+ " trips on the page before deleting");
		pasService.countTripsonInDB(queryAsPassenger);
		numFromBaseAsPassBeforeDelet = pasService.numPassPage;
		System.out.println("Passenger has " + numFromBaseAsPassBeforeDelet
				+ " trips in the DB before deleting");
		driverService = new DriverService();
		driverService.deletingTripAsDriver(driver_username, driver_password, idtr);
		driverService.countTripsOnPage(driver_username, driver_password);
		numFromPageAsDriverAfterDelet = driverService.numDriverPage;
		System.out.println("Driver has " + numFromPageAsDriverAfterDelet
				+ " trips on the page after deleting");
		driverService.countTripsinDB(queryAsDriver);
		numFromBaseAsDriverAfterDelet = driverService.numDriverBase;
		System.out.println("Driver has " + numFromBaseAsDriverAfterDelet
				+ " trips in the DB after deleting");
		pasService = new PassengerService();
		pasService.countTripsonPage(passenger_username, passenger_password);
		numFromPageAsPassAfterDelet = pasService.numPassPage;
		System.out.println("Passenger has " + numFromPageAsPassAfterDelet
				+ " trips on page after deleting");
		pasService.countTripsonInDB(queryAsPassenger);
		numFromBaseAsPassAfterDelet = pasService.numPassBase;
		System.out.println("Passenger has " + numFromBaseAsPassAfterDelet
				+ " trips in the DB after deleting");
		numbersOfTrips = pasService.goToVerifyTrips();
		numbersOfTrips.VerifyNumberOfTripsOnthePageAsDriver(
				numFromPageAsDriverBeforeDelet, numFromPageAsDriverAfterDelet);
		numbersOfTrips.VerifyNumberOfTripsInTheDBAsDriver(
				numFromBaseAsDriverBeforeDelet, numFromBaseAsDriverAfterDelet);
		numbersOfTrips.VerifyNumberOfTripsOnthePageAsPassenger(
				numFromPageAsPassBeforeDelet, numFromPageAsPassAfterDelet);
		numbersOfTrips.VerifyNumberOfTripsInTheDBAsPassenger(
				numFromBaseAsPassBeforeDelet, numFromBaseAsPassAfterDelet);
		pasService = new PassengerService();
		pasService.deletingPassFromDB(queryDeletingAllPassengerTrips);
		driverService = new DriverService();
		driverService.deletingDriverFromDB(queryDeletingAllDriverTrips);
	}

	@After
	public void afterTest() throws SQLException {
		Driver.tearDown();
	}
}
