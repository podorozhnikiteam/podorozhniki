package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import com.epam.podorozhniki.core.Driver;

/*
 * Created by Zoja_Sharova
 */

public class TC_1128_1_1 extends BaseActions {

	public TC_1128_1_1() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private DeletingTrip deletingTrips; 

	public String queryDeletingAllDriverTrips;

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;

	private static Logger log = Logger.getLogger(TC_1128_1_1.class);

	// check page after deleting trip without passenger 
	@Test
	public void withoutPassOnPage() throws InterruptedException, SQLException {
		log.info(" GET STARTED");
		deletingTrips = new DeletingTrip();
		deletingTrips.deletingTripWithoutPassenger();
		numFromPageAsDriverBeforeDelet = deletingTrips.numFromPageAsDriverBeforeDelet;
		numFromPageAsDriverAfterDelet = deletingTrips.numFromPageAsDriverAfterDelet;
		deletingTrips.verifyNumberOfTripsOnthePage(
				numFromPageAsDriverBeforeDelet, numFromPageAsDriverAfterDelet);
		log.info("OK: Verifying number of trips on the page as driver");
	}

}