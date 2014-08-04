package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MethodsPage;

public class TC_1 extends MethodsPage {

	public TC_1() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private DriverService driverService;
	private VerifyNumbersOfTrips numbersOfTrips;
	private DBService dbService;
	private TripWihoutPass withoutPass;

	public String driver_username;
	public String driver_password;

//	public String idtr;
//	public String idtr_for_delete;
	public String queryDeletingAllDriverTrips;
	public String queryAsDriver;

	public String from_address;
	public String to_address;

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;
	protected int numFromBaseAsDriverBeforeDelet;
	protected int numFromBaseAsDriverAfterDelet;

	private static Logger log = Logger.getLogger(TC_1128_1.class);

	@Before
	public void setUp() {
		Driver.init();
		driver_username = System.getProperty("US1128.driver_login");
		driver_password = System.getProperty("US1128.driver_password");
		queryDeletingAllDriverTrips = System
				.getProperty("US1128.queryDeletingAllDriverTrips");
		queryAsDriver = System.getProperty("US1128.queryAsDriver");
		from_address = System.getProperty("US1128.from_adress");
		to_address = System.getProperty("US1128.to_adress");
		Driver.getInstance().manage().window().maximize();
		Driver.getInstance().get(
				"http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main");
	}

	@Test
	public void TC_1128_1_1() throws InterruptedException, SQLException {
		withoutPass = new TripWihoutPass();
		withoutPass.withoutPassCheckTrips();
		numbersOfTrips = withoutPass.withoutPassCheckTrips();
		numFromPageAsDriverBeforeDelet = withoutPass.numFromPageAsDriverBeforeDelet;
		numFromPageAsDriverAfterDelet = withoutPass.numFromPageAsDriverAfterDelet;
		System.out.println("numFromBaseAsDriverBeforeDelet "
				+ numFromBaseAsDriverBeforeDelet);
		System.out.println("numFromPageAsDriverAfterDelet "
				+ numFromPageAsDriverAfterDelet);
		numbersOfTrips.VerifyNumberOfTripsOnthePageAsDriver(
				numFromPageAsDriverBeforeDelet, numFromPageAsDriverAfterDelet);
	}

	@Test
	public void TC_1128_1_2() throws InterruptedException, SQLException {
		withoutPass = new TripWihoutPass();
		withoutPass.withoutPassCheckTrips();
		numbersOfTrips = withoutPass.withoutPassCheckTrips();
		numFromBaseAsDriverBeforeDelet = withoutPass.numFromBaseAsDriverAfterDelet;
		numFromBaseAsDriverAfterDelet = withoutPass.numFromBaseAsDriverAfterDelet;
		System.out.println("numFromBaseAsDriverBeforeDelet "
				+ numFromBaseAsDriverBeforeDelet);
		System.out.println("numFromBaseAsDriverAfterDelet "
				+ numFromBaseAsDriverAfterDelet);
		numbersOfTrips.VerifyNumberOfTripsInTheDBAsDriver(
				numFromBaseAsDriverBeforeDelet, numFromBaseAsDriverAfterDelet);
	}

	@After
	public void afterTest() throws SQLException {
		dbService = new DBService();
		dbService.deletingTripsAsDriver(queryDeletingAllDriverTrips);
		Driver.tearDown();
	}
}