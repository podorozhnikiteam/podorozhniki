package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;

public class TC_1128_2_4 extends BaseActions {

	public TC_1128_2_4() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private DeletingTrip deletingTrip;

	public String queryDeletingAllDriverTrips;
	public String queryDeletingAllPassengerTrips;

	protected int numFromBaseAsPassBeforeDelet;
	protected int numFromBaseAsPassAfterDelet;

	private static Logger log = Logger.getLogger(TC_1128_2_4.class);


	@Test
	public void withPassInBaseAsPass() throws InterruptedException, SQLException {
		log.info(" GET STARTED");
		deletingTrip = new DeletingTrip();
		deletingTrip.deletingTripWithPassenger();
		numFromBaseAsPassBeforeDelet = deletingTrip.numFromBaseAsPassBeforeDelet;
		numFromBaseAsPassAfterDelet = deletingTrip.numFromBaseAsPassAfterDelet;
		deletingTrip.verifyNumberOfTripsOnthePage(
				numFromBaseAsPassBeforeDelet, numFromBaseAsPassAfterDelet);
		log.info("OK: Verifying number of trips in the database as passenger");
	}

}
