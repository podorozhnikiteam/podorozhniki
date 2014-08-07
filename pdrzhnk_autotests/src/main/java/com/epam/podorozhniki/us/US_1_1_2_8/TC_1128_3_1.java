package com.epam.podorozhniki.us.US_1_1_2_8;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import com.epam.podorozhniki.ui.MyTripsPage;
import com.epam.podorozhniki.us.US_1_1_2_8.ReadingDatafile;

/*
 * Created by Zoja_Sharova
 */

public class TC_1128_3_1 extends BaseActions {

	public TC_1128_3_1() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	public String idtr;
	public String idtr_for_delete;
	
	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;

	private ReadingDatafile rd;
	private DeletingTrip deletingTrips; 
	private US_Methods us_Methods;
	
	private static Logger log = Logger.getLogger(TC_1128_3_1.class);

	// 
	@Test
	public void deletingTripsWithAcceptedStatus() throws SQLException,
			InterruptedException {
		deletingTrips = new DeletingTrip();
		deletingTrips.deletingTripWithPassenger();
		numFromPageAsDriverBeforeDelet = deletingTrips.numFromPageAsDriverBeforeDelet;
		numFromPageAsDriverAfterDelet = deletingTrips.numFromPageAsDriverAfterDelet;
		deletingTrips.verifyNumberOfTripsOnthePage(
				numFromPageAsDriverBeforeDelet, numFromPageAsDriverAfterDelet);
		log.info("OK: Verifying number of trips on the page as driver");
	}
}
