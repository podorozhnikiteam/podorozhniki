package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.remote.ErrorHandler.UnknownServerException;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MethodsPage;

public class TripWihoutPass extends MethodsPage {

	public TripWihoutPass() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private DriverService driverService;

	public String driver_username;
	public String driver_password;

	public String idtr;
	public String idtr_for_delete;
	public String queryDeletingAllDriverTrips;
	public String queryAsDriver;

	public String from_address;
	public String to_address;

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;
	protected int numFromBaseAsDriverBeforeDelet;
	protected int numFromBaseAsDriverAfterDelet;

	private static Logger log = Logger.getLogger(TripWihoutPass.class);

	@Test
	public void withoutPassCheckTrips()
			throws InterruptedException, SQLException, UnknownServerException {

		driver_username = System.getProperty("US1128.driver_login");
		driver_password = System.getProperty("US1128.driver_password");
		queryDeletingAllDriverTrips = System
				.getProperty("US1128.queryDeletingAllDriverTrips");
		queryAsDriver = System.getProperty("US1128.queryAsDriver");
		
		driverService = new DriverService();
		driverService.addingTrips(driver_username, driver_password);
		driverService.countTripsOnPage(driver_username, driver_password);
		numFromPageAsDriverBeforeDelet = driverService.numDriverPage;
		log.info("Driver has " + numFromPageAsDriverBeforeDelet
				+ " trips on the page before deleting");
		driverService.countTripsinDB(queryAsDriver);
		numFromBaseAsDriverBeforeDelet = driverService.numDriverBase;
		idtr = driverService.idtr;
		idtr_for_delete = idtr;
		log.info("Driver has " + numFromBaseAsDriverBeforeDelet
				+ " trips in the DB before deleting");
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
	}
}
