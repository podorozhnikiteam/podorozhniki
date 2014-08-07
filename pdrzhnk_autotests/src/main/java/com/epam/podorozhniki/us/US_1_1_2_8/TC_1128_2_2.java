package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MethodsPage;

/*
 * Created by Zoja_Sharova
 */

public class TC_1128_2_2 extends BaseActions {

	public TC_1128_2_2() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private DeletingTrip deletingTrip;

	public String queryDeletingAllDriverTrips;
	public String queryDeletingAllPassengerTrips;

	protected int numFromBaseAsDriverBeforeDelet;
	protected int numFromBaseAsDriverAfterDelet;

	private static Logger log = Logger.getLogger(TC_1128_2_2.class);
	
	@FindBy(xpath = "//li[@id='li_driver']/a")
	protected WebElement asDriverTab;

	// check driver database after deleting trip with passenger
	@Test
	public void withPassInBaseAsDriver() throws InterruptedException,
			SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		log.info(" GET STARTED");
		deletingTrip = new DeletingTrip();
		deletingTrip.deletingTripWithPassenger("accept0", asDriverTab);
		numFromBaseAsDriverBeforeDelet = deletingTrip.numFromBaseAsDriverBeforeDelet;
		numFromBaseAsDriverAfterDelet = deletingTrip.numFromBaseAsDriverAfterDelet;
		deletingTrip.verifyNumberOfTripsOnthePage(
				numFromBaseAsDriverBeforeDelet, numFromBaseAsDriverAfterDelet);
		log.info("OK: Verifying number of trips in the database as driver");
	}

}
