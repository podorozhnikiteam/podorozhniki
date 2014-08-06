package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;

/*
 * Created by Zoja_Sharova
 */

public class TC_1128_2_3 extends BaseActions {

	public TC_1128_2_3() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private DeletingTrip deletingTrip;

	public String queryDeletingAllDriverTrips;
	public String queryDeletingAllPassengerTrips;

	protected int numFromPageAsPassBeforeDelet;
	protected int numFromPageAsPassAfterDelet;

	private static Logger log = Logger.getLogger(TC_1128_2_3.class);

	@Test
	public void withPassOnPageAsPass() throws InterruptedException,
			SQLException {
		log.info(" GET STARTED");
		deletingTrip = new DeletingTrip();
		deletingTrip.deletingTripWithPassenger();
		numFromPageAsPassBeforeDelet = deletingTrip.numFromPageAsPassBeforeDelet;
		numFromPageAsPassAfterDelet = deletingTrip.numFromPageAsPassAfterDelet;
		deletingTrip.verifyNumberOfTripsOnthePage(numFromPageAsPassBeforeDelet,
				numFromPageAsPassAfterDelet);
		log.info("OK: Verifying number of trips on the page as passenger");
	}

}
