package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MethodsPage;

public class TC_1_1 extends MethodsPage {

	public TC_1_1() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private DBService dbService;
	private TripWihoutPass withoutPass;

	public String queryDeletingAllDriverTrips;

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;

	private static Logger log = Logger.getLogger(TC_1_1.class);

	@Before
	public void setUp() {
		log.info(" GET STARTED");
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
	public void withoutPassOnPage() throws InterruptedException, SQLException {
		withoutPass = new TripWihoutPass();
		withoutPass.withoutPassCheckTrips();
		numFromPageAsDriverBeforeDelet = withoutPass.numFromPageAsDriverBeforeDelet;
		numFromPageAsDriverAfterDelet = withoutPass.numFromPageAsDriverAfterDelet;
		withoutPass.verifyNumberOfTripsOnthePage(numFromPageAsDriverBeforeDelet, numFromPageAsDriverAfterDelet);
		log.info("OK: Verifying number of trips on the page as driver");
	}

	
}