package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;

public class TC_1_2 extends BaseActions {

	public TC_1_2() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private TripWihoutPass withoutPass;

	public String queryDeletingAllDriverTrips;

	protected int numFromBaseAsDriverBeforeDelet;
	protected int numFromBaseAsDriverAfterDelet;

	private static Logger log = Logger.getLogger(TC_1_2.class);

	@Test
	public void withoutPassInBase() throws InterruptedException, SQLException {
		withoutPass = new TripWihoutPass();
		withoutPass.withoutPassMethod();
		numFromBaseAsDriverBeforeDelet = withoutPass.numFromBaseAsDriverBeforeDelet;
		numFromBaseAsDriverAfterDelet = withoutPass.numFromBaseAsDriverAfterDelet;
		withoutPass.verifyNumberOfTripsOnthePage(
				numFromBaseAsDriverBeforeDelet, numFromBaseAsDriverAfterDelet);
		log.info("OK: Verifying number of trips in the database as driver");
	}

}