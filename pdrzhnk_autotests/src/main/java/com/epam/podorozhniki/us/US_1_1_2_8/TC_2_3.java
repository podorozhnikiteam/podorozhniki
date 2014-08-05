package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;

public class TC_2_3 extends BaseActions {

	public TC_2_3() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private TripWithPass tripWithPass;

	public String queryDeletingAllDriverTrips;
	public String queryDeletingAllPassengerTrips;

	protected int numFromPageAsPassBeforeDelet;
	protected int numFromPageAsPassAfterDelet;

	private static Logger log = Logger.getLogger(TC_2_3.class);

	@Test
	public void withPassOnPageAsPass() throws InterruptedException,
			SQLException {
		tripWithPass = new TripWithPass();
		tripWithPass.withPassMetod();
		numFromPageAsPassBeforeDelet = tripWithPass.numFromPageAsPassBeforeDelet;
		numFromPageAsPassAfterDelet = tripWithPass.numFromPageAsPassAfterDelet;
		tripWithPass.verifyNumberOfTripsOnthePage(numFromPageAsPassBeforeDelet,
				numFromPageAsPassAfterDelet);
		log.info("OK: Verifying number of trips on the page as passenger");
	}

}
