package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;

public class TC_1_1 extends BaseActions {

	public TC_1_1() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private TripWihoutPass withoutPass;

	public String queryDeletingAllDriverTrips;

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;

	private static Logger log = Logger.getLogger(TC_1_1.class);

	@Test
	public void withoutPassOnPage() throws InterruptedException, SQLException {
		log.info(" GET STARTED");
		withoutPass = new TripWihoutPass();
		withoutPass.withoutPassMethod();
		numFromPageAsDriverBeforeDelet = withoutPass.numFromPageAsDriverBeforeDelet;
		numFromPageAsDriverAfterDelet = withoutPass.numFromPageAsDriverAfterDelet;
		withoutPass.verifyNumberOfTripsOnthePage(
				numFromPageAsDriverBeforeDelet, numFromPageAsDriverAfterDelet);
		log.info("OK: Verifying number of trips on the page as driver");
	}

}