package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.http.impl.conn.Wire;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MethodsPage;
import com.google.common.util.concurrent.CycleDetectingLockFactory.WithExplicitOrdering;

public class TC_1_2 extends MethodsPage {

	public TC_1_2() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private DBService dbService;
	private TripWihoutPass withoutPass;

	public String queryDeletingAllDriverTrips;

	protected int numFromBaseAsDriverBeforeDelet;
	protected int numFromBaseAsDriverAfterDelet;

	private static Logger log = Logger.getLogger(TC_1_2.class);

	@Before
	public void setUp() {
		log.info("GET STARTED");
		Driver.init();
		Driver.getInstance().manage().window().maximize();
		Driver.getInstance().get(
				"http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main");
		queryDeletingAllDriverTrips = System
				.getProperty("US1128.queryDeletingAllDriverTrips");
	}
	
	@After
	public void afterTest() throws SQLException {
		dbService = new DBService();
		dbService.deletingTripsAsDriver(queryDeletingAllDriverTrips);
		Driver.tearDown();
	}

	@Test
	public void withoutPassInBase() throws InterruptedException, SQLException {
		withoutPass = new TripWihoutPass();
		withoutPass.withoutPassCheckTrips();
		numFromBaseAsDriverBeforeDelet = withoutPass.numFromBaseAsDriverBeforeDelet;
		numFromBaseAsDriverAfterDelet = withoutPass.numFromBaseAsDriverAfterDelet;
		withoutPass.verifyNumberOfTripsOnthePage(numFromBaseAsDriverBeforeDelet, numFromBaseAsDriverAfterDelet);
		log.info("OK: Verifying number of trips in the database as driver");
	}

	
}