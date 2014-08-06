package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;

public class TC_2_1 extends BaseActions {

	public TC_2_1() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private TripWithPass tripWithPass;

	public String queryDeletingAllDriverTrips;
	public String queryDeletingAllPassengerTrips;

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;

	private static Logger log = Logger.getLogger(TC_2_1.class);

	@Test
	public void withPassOnPageAsDriver() throws InterruptedException,
			SQLException {
		log.info(" GET STARTED");
		tripWithPass = new TripWithPass();
		tripWithPass.withPassMetod();
		numFromPageAsDriverBeforeDelet = tripWithPass.numFromPageAsDriverBeforeDelet;
		numFromPageAsDriverAfterDelet = tripWithPass.numFromPageAsDriverAfterDelet;
		tripWithPass.verifyNumberOfTripsOnthePage(
				numFromPageAsDriverBeforeDelet, numFromPageAsDriverAfterDelet);
		log.info("OK: Verifying number of trips on the page as driver");
	}

}
