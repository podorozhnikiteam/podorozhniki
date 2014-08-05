package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MethodsPage;

public class TC_2_3 extends MethodsPage {

	public TC_2_3() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private DBService dbService;
	private TripWithPass tripWithPass;

	public String queryDeletingAllDriverTrips;
	public String queryDeletingAllPassengerTrips;

	protected int numFromPageAsPassBeforeDelet;
	protected int numFromPageAsPassAfterDelet;

	private static Logger log = Logger.getLogger(TC_2_3.class);

	@Before
	public void setUp() {
		log.info(" GET STARTED");
		Driver.init();
		Driver.getInstance().manage().window().maximize();
		Driver.getInstance().get(
				"http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main");
		queryDeletingAllDriverTrips = System
				.getProperty("US1128.queryDeletingAllDriverTrips");
		queryDeletingAllPassengerTrips = System
				.getProperty("US1128.queryDeletingAllPassengerTrips");
	}

	@After
	public void afterTest() throws SQLException {
		dbService = new DBService();
		dbService.deletingTripsAsPassenger(queryDeletingAllPassengerTrips);
		dbService.deletingTripsAsDriver(queryDeletingAllDriverTrips);
		Driver.tearDown();
	}

	@Test
	public void withPassOnPageAsPass() throws InterruptedException,
			SQLException {
		tripWithPass = new TripWithPass();
		tripWithPass.withPassCheckTrips();
		numFromPageAsPassBeforeDelet = tripWithPass.numFromPageAsPassBeforeDelet;
		numFromPageAsPassAfterDelet = tripWithPass.numFromPageAsPassAfterDelet;
		tripWithPass.verifyNumberOfTripsOnthePage(
				numFromPageAsPassBeforeDelet, numFromPageAsPassAfterDelet);
		log.info("OK: Verifying number of trips on the page as passenger");
	}

}
