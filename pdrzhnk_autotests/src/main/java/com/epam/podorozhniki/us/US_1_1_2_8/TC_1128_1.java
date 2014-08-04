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
	private DBService dbService;

	public String driver_username;
	public String driver_password;
	public String passenger_username;
	public String passenger_password;

	public String idtr;
	public String idtr_for_delete;
	public String queryDeletingAllDriverTrips;
	public String queryAsDriver;
	public String queryAsPassenger;
	public String queryDeletingAllPassengerTrips;

	public String from_address;
	public String to_address;

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;
	protected int numFromBaseAsDriverBeforeDelet;
	protected int numFromBaseAsDriverAfterDelet;
	protected int numFromPageAsPassBeforeDelet;
	protected int numFromPageAsPassAfterDelet;
	protected int numFromBaseAsPassBeforeDelet;
	protected int numFromBaseAsPassAfterDelet;

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
		from_address = System.getProperty("US1128.from_adress");
		to_address = System.getProperty("US1128.to_adress");
		Driver.getInstance().manage().window().maximize();
		Driver.getInstance().get(
				"http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main");
	}

	@Test
	public void TC_1128_1_1 () throws InterruptedException,
			SQLException {
		driverService = new DriverService();
		driverService.addingTrips(driver_username, driver_password);
		driverService.countTripsOnPage(driver_username, driver_password);
		numFromPageAsDriverBeforeDelet = driverService.numDriverPage;
		System.out.println("Driver has " + numFromPageAsDriverBeforeDelet
				+ " trips on the page before deleting");
		driverService.countTripsinDB(queryAsDriver);
		numFromBaseAsDriverBeforeDelet = driverService.numDriverBase;
		idtr = driverService.idtr;
		idtr_for_delete = idtr;
		System.out.println("Driver has " + numFromBaseAsDriverBeforeDelet
				+ " trips in the DB before deleting");
		driverService.deletingTripAsDriver(driver_username, driver_password, idtr_for_delete);
		driverService.countTripsOnPage(driver_username, driver_password);
		numFromPageAsDriverAfterDelet = driverService.numDriverPage;
		System.out.println("Driver has " + numFromPageAsDriverAfterDelet
				+ " trips on the page after deleting");
		driverService.countTripsinDB(queryAsDriver);
		numFromBaseAsDriverAfterDelet = driverService.numDriverBase;
		System.out.println("Driver has " + numFromBaseAsDriverAfterDelet
				+ " trips in the DB after deleting");
		numbersOfTrips = driverService.goToVerifyTrips();
		numbersOfTrips.VerifyNumberOfTripsOnthePageAsDriver(
				numFromPageAsDriverBeforeDelet, numFromPageAsDriverAfterDelet);
		numbersOfTrips.VerifyNumberOfTripsInTheDBAsDriver(
				numFromBaseAsDriverBeforeDelet, numFromBaseAsDriverAfterDelet);
	}

	@After
	public void afterTest() throws SQLException {
		dbService = new DBService();
		dbService.deletingTripsAsPassenger(queryDeletingAllPassengerTrips);
		dbService.deletingTripsAsDriver(queryDeletingAllDriverTrips);
		Driver.tearDown();
	}
}
