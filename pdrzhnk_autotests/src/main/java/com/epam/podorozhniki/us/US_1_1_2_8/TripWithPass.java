package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MethodsPage;

public class TripWithPass extends MethodsPage {

	public TripWithPass() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private DriverService driverService;
	private PassengerService pasService;

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

	private static Logger log = Logger.getLogger(TripWithPass.class);
	
	public void withPassCheckTrips() throws InterruptedException, SQLException {
		passenger_username = System.getProperty("US1128.passenger_login");
		passenger_password = System.getProperty("US1128.passenger_password");
		driver_username = System.getProperty("US1128.driver_login");
		driver_password = System.getProperty("US1128.driver_password");
		queryDeletingAllDriverTrips = System
				.getProperty("US1128.queryDeletingAllDriverTrips");
		queryAsDriver = System.getProperty("US1128.queryAsDriver");
		queryAsPassenger = System.getProperty("US1128.queryAsPassenger");
		queryDeletingAllPassengerTrips = System
				.getProperty("US1128.queryDeletingAllPassengerTrips");
		from_address = System.getProperty("US1128.from_adress");
		to_address = System.getProperty("US1128.to_adress");
		driverService = new DriverService();
		driverService.addingTrips(driver_username, driver_password);
		driverService.countTripsOnPage(driver_username, driver_password);
		numFromPageAsDriverBeforeDelet = driverService.numDriverPage;
		idtr = driverService.idtr;
		log.info("Driver has " + numFromPageAsDriverBeforeDelet
				+ " trips on the page before deleting, idtr = " + idtr);
		driverService.countTripsinDB(queryAsDriver);
		numFromBaseAsDriverBeforeDelet = driverService.numDriverBase;
		log.info("Driver has " + numFromBaseAsDriverBeforeDelet
				+ " trips in the DB before deleting");
		pasService = new PassengerService();
		pasService.joinToTrip(passenger_username, passenger_password,
				from_address, to_address, idtr);
		pasService.countTripsonPage(passenger_username, passenger_password);
		numFromPageAsPassBeforeDelet = pasService.numPassPage;
		log.info("Passenger has " + numFromPageAsPassBeforeDelet
				+ " trips on the page before deleting");
		pasService.countTripsonInDB(queryAsPassenger);
		numFromBaseAsPassBeforeDelet = pasService.numPassBase;
		log.info("Passenger has " + numFromBaseAsPassBeforeDelet
				+ " trips in the DB before deleting");
		driverService = new DriverService();
		idtr_for_delete = idtr;
		driverService.deletingTripAsDriver(driver_username, driver_password,
				idtr_for_delete);
		driverService.countTripsOnPage(driver_username, driver_password);
		numFromPageAsDriverAfterDelet = driverService.numDriverPage;
		log.info("Driver has " + numFromPageAsDriverAfterDelet
				+ " trips on the page after deleting");
		driverService.countTripsinDB(queryAsDriver);
		numFromBaseAsDriverAfterDelet = driverService.numDriverBase;
		log.info("Driver has " + numFromBaseAsDriverAfterDelet
				+ " trips in the DB after deleting");
		pasService = new PassengerService();
		pasService.countTripsonPage(passenger_username, passenger_password);
		numFromPageAsPassAfterDelet = pasService.numPassPage;
		log.info("Passenger has " + numFromPageAsPassAfterDelet
				+ " trips on page after deleting");
		pasService.countTripsonInDB(queryAsPassenger);
		numFromBaseAsPassAfterDelet = pasService.numPassBase;
		log.info("Passenger has " + numFromBaseAsPassAfterDelet
				+ " trips in the DB after deleting");
	}
}
