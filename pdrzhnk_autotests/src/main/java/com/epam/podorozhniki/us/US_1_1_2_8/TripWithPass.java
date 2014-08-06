package com.epam.podorozhniki.us.US_1_1_2_8;

import static org.junit.Assert.assertFalse;

import java.sql.SQLException;

import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import com.epam.podorozhniki.ui.MethodsPage;

public class TripWithPass extends MethodsPage {

	public TripWithPass() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private DriverService driverService;
	private PassengerService pasService;
	private ReadingDatafile rd;
	
	public String idtr;
	public String idtr_for_delete;

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;
	protected int numFromBaseAsDriverBeforeDelet;
	protected int numFromBaseAsDriverAfterDelet;
	protected int numFromPageAsPassBeforeDelet;
	protected int numFromPageAsPassAfterDelet;
	protected int numFromBaseAsPassBeforeDelet;
	protected int numFromBaseAsPassAfterDelet;

	private static Logger log = Logger.getLogger(TripWithPass.class);

	public void withPassMetod() throws InterruptedException, SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		driverService = new DriverService();
		driverService.addingTrips(rd.driver_username, rd.driver_password);
		driverService.countTripsOnPage(rd.driver_username, rd.driver_password);
		numFromPageAsDriverBeforeDelet = driverService.numDriverPage;
		idtr = driverService.idtr;
		log.info("Driver has " + numFromPageAsDriverBeforeDelet
				+ " trips on the page before deleting, idtr = " + idtr);
		log.info("Driver counts trips in the DB");
		numFromBaseAsDriverBeforeDelet = queryGetInt(rd.queryAsDriver);
		log.info("Driver has " + numFromBaseAsDriverBeforeDelet
				+ " trips in the DB before deleting");
		pasService = new PassengerService();
		pasService.joinToTrip(rd.passenger_username, rd.passenger_password,
				rd.from_address, rd.to_address, idtr);
		pasService.countTripsonPage(rd.passenger_username,
				rd.passenger_password);
		numFromPageAsPassBeforeDelet = pasService.numPassPage;
		log.info("Passenger has " + numFromPageAsPassBeforeDelet
				+ " trips on the page before deleting");
		log.info("Passenger counts trips in the DB");
		numFromBaseAsPassBeforeDelet = queryGetInt(rd.queryAsPassenger);
		log.info("Passenger has " + numFromBaseAsPassBeforeDelet
				+ " trips in the DB before deleting");
		driverService = new DriverService();
		idtr_for_delete = idtr;
		driverService.deletingTripAsDriver(rd.driver_username,
				rd.driver_password, idtr_for_delete);
		driverService.countTripsOnPage(rd.driver_username, rd.driver_password);
		numFromPageAsDriverAfterDelet = driverService.numDriverPage;
		log.info("Driver has " + numFromPageAsDriverAfterDelet
				+ " trips on the page after deleting");
		log.info("Driver counts trips in the DB");
		numFromBaseAsDriverAfterDelet = queryGetInt(rd.queryAsDriver);
		log.info("Driver has " + numFromBaseAsDriverAfterDelet
				+ " trips in the DB after deleting");
		pasService = new PassengerService();
		pasService.countTripsonPage(rd.passenger_username,
				rd.passenger_password);
		numFromPageAsPassAfterDelet = pasService.numPassPage;
		log.info("Passenger has " + numFromPageAsPassAfterDelet
				+ " trips on page after deleting");
		log.info("Passenger counts trips in the DB");
		numFromBaseAsPassAfterDelet = queryGetInt(rd.queryAsPassenger);
		log.info("Passenger has " + numFromBaseAsPassAfterDelet
				+ " trips in the DB after deleting");
	}
	

	public String withPassAddingTripsWithoutCount() throws InterruptedException, SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		driverService = new DriverService();
		driverService.addingTrips(rd.driver_username, rd.driver_password);
		idtr = driverService.idtr;
		pasService = new PassengerService();
		pasService.joinToTrip(rd.passenger_username, rd.passenger_password,
				rd.from_address, rd.to_address, idtr);
		driverService = new DriverService();
		idtr_for_delete = idtr;
		driverService.deletingTripAsDriver(rd.driver_username,
				rd.driver_password, idtr_for_delete);
		driverService.logout();
		return idtr; 
	}
}
