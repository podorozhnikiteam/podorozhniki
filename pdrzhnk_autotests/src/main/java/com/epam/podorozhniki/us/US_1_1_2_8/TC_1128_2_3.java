package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MyTripsPage;

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

//
	@FindBy(xpath = "//li[@id='li_passenger']/a")
	protected WebElement asPassengerTab;

	@FindBy(xpath = "//li[@id='li_driver']/a")
	protected WebElement asDriverTab;
//	
	//  check passenger page after deleting trip with passenger status accepted 
	@Test
	public void withPassOnPageAsPass() throws InterruptedException,
			SQLException {
		log.info(" GET STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		deletingTrip = new DeletingTrip();
		deletingTrip.deletingTripWithPassenger("accept0", asPassengerTab);
		numFromPageAsPassBeforeDelet = deletingTrip.numFromPageAsPassBeforeDelet;
		numFromPageAsPassAfterDelet = deletingTrip.numFromPageAsPassAfterDelet;
		deletingTrip.verifyNumberOfTripsOnthePage(numFromPageAsPassBeforeDelet,
				numFromPageAsPassAfterDelet);
		log.info("OK: Verifying number of trips on the page as passenger");
	}

}
