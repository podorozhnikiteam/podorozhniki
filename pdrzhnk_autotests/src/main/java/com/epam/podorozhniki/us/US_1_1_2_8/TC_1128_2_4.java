package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;

/*
 * Created by Zoja_Sharova
 */

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
	

	@FindBy(xpath = "//li[@id='li_passenger']/a")
	protected WebElement asPassengerTab;

	@FindBy(xpath = "//li[@id='li_driver']/a")
	protected WebElement asDriverTab;

	// check passenger database after deleting trip with passenger status accepted 
	@Test
	public void withPassInBaseAsPass() throws InterruptedException, SQLException {
		log.info(" GET STARTED");
		rd = new ReadingDatafile(); 
		rd.readingDataFile();
		deletingTrip = new DeletingTrip();
		deletingTrip.deletingTripWithPassenger(rd.accepted, asPassengerTab);
		numFromBaseAsPassBeforeDelet = deletingTrip.numFromBaseAsPassBeforeDelet;
		numFromBaseAsPassAfterDelet = deletingTrip.numFromBaseAsPassAfterDelet;
		deletingTrip.verifyNumberOfTripsOnthePage(
				numFromBaseAsPassBeforeDelet, numFromBaseAsPassAfterDelet);
		log.info("OK: Verifying number of trips in the database as passenger");
	}

}
