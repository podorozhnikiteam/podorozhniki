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
	private ReadingDatafile rd; 

	public String idtr;
	public String idtr_for_delete;

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;
	protected int numFromBaseAsDriverBeforeDelet;
	protected int numFromBaseAsDriverAfterDelet;

	private static Logger log = Logger.getLogger(TripWihoutPass.class);

	@Test
	public void withoutPassMethod()
			throws InterruptedException, SQLException, UnknownServerException {
		log.info(" withoutPassMethod STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		driverService = new DriverService();
		driverService.addingTrips(rd.driver_username, rd.driver_password);
		driverService.countTripsOnPage(rd.driver_username, rd.driver_password);
		numFromPageAsDriverBeforeDelet = driverService.numDriverPage;
		log.info("Driver has " + numFromPageAsDriverBeforeDelet
				+ " trips on the page before deleting");
		log.info("Driver counts trips in the DB");
		numFromBaseAsDriverBeforeDelet = queryGetInt(rd.queryAsDriver); 
		idtr = driverService.idtr;
		idtr_for_delete = idtr;
		log.info("Driver has " + numFromBaseAsDriverBeforeDelet
				+ " trips in the DB before deleting");
		driverService.deletingTripAsDriver(rd.driver_username, rd.driver_password,
				idtr_for_delete);
		driverService.countTripsOnPage(rd.driver_username, rd.driver_password);
		numFromPageAsDriverAfterDelet = driverService.numDriverPage;
		log.info("Driver has " + numFromPageAsDriverAfterDelet
				+ " trips on the page after deleting");
		log.info("Driver counts trips in the DB");
		numFromBaseAsDriverAfterDelet = queryGetInt(rd.queryAsDriver); 
		log.info("Driver has " + numFromBaseAsDriverAfterDelet
				+ " trips in the DB after deleting");
	}
	
	public void addingTripsWithoutCount() throws InterruptedException, SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		driverService = new DriverService();
		driverService.addingTrips(rd.driver_username, rd.driver_password);
		idtr = driverService.idtr;
		idtr_for_delete = idtr;
		driverService.deletingTripAsDriver(rd.driver_username,
				rd.driver_password, idtr_for_delete);
		driverService.logout();
	}
}
