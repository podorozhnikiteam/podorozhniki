package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MethodsPage;

public class TC_2_2 extends BaseActions {

	public TC_2_2() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private TripWithPass tripWithPass;

	public String queryDeletingAllDriverTrips;
	public String queryDeletingAllPassengerTrips;

	protected int numFromBaseAsDriverBeforeDelet;
	protected int numFromBaseAsDriverAfterDelet;

	private static Logger log = Logger.getLogger(TC_2_2.class);

	@Test
	public void withPassInBaseAsDriver() throws InterruptedException,
			SQLException {
		log.info(" GET STARTED");
		tripWithPass = new TripWithPass();
		tripWithPass.withPassMetod();
		numFromBaseAsDriverBeforeDelet = tripWithPass.numFromBaseAsDriverBeforeDelet;
		numFromBaseAsDriverAfterDelet = tripWithPass.numFromBaseAsDriverAfterDelet;
		tripWithPass.verifyNumberOfTripsOnthePage(
				numFromBaseAsDriverBeforeDelet, numFromBaseAsDriverAfterDelet);
		log.info("OK: Verifying number of trips in the database as driver");
	}

}
