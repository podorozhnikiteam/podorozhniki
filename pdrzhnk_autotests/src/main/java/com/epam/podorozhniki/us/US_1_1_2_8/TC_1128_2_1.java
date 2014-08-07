package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.db.DBConnection;

/*
 * Created by Zoja_Sharova
 */

public class TC_1128_2_1 extends BaseActions {

	public TC_1128_2_1() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private DeletingTrip deletingTrip;

	public String queryDeletingAllDriverTrips;
	public String queryDeletingAllPassengerTrips;

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;

	private static Logger log = Logger.getLogger(TC_1128_2_1.class);
	
	@FindBy(xpath = "//li[@id='li_driver']/a")
	protected WebElement asDriverTab;
	
	// check driver page after deleting trip with passenger status accepted 
	@Test
	public void withPassOnPageAsDriverAcceptedStatus() throws InterruptedException,
			SQLException {
		log.info(" GET STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		deletingTrip = new DeletingTrip();
		deletingTrip.deletingTripWithPassenger("accept0", asDriverTab);
		numFromPageAsDriverBeforeDelet = deletingTrip.numFromPageAsDriverBeforeDelet;
		numFromPageAsDriverAfterDelet = deletingTrip.numFromPageAsDriverAfterDelet;
		deletingTrip.verifyNumberOfTripsOnthePage(
				numFromPageAsDriverBeforeDelet, numFromPageAsDriverAfterDelet);
		log.info("OK: Verifying number of trips on the page as driver");
	}

}
