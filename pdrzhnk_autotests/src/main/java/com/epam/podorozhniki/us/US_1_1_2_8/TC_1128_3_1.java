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

	private US_Methods us_Methods;
	private static Logger log = Logger.getLogger(TC_1128_3_1.class);

	private By buttonDetails = By.xpath("//a[contains(text(),'Details')]");
	private By nextPageDriverTab = By
			.xpath("//div[@id='routeResults']//li[3]/a");

	@Test
	public void deletingTripsWithAcceptedStatus() throws SQLException,
			InterruptedException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		rd = new ReadingDatafile();
		rd.readingDataFile();
		us_Methods = new US_Methods();
		us_Methods.addingTrips(rd.driver_username, rd.driver_password);
		idtr = us_Methods.idtr;
		us_Methods.joinToTrip(rd.passenger_username, rd.passenger_password,
				rd.from_address, rd.to_address, idtr);
		idtr_for_delete = idtr;
		us_Methods.goToAsDRiverTab(rd.driver_username, rd.driver_password);
		us_Methods.goToSubmittedStatus(rd.driver_username, rd.driver_password, idtr);
		us_Methods.deletingTripAsDriver(rd.driver_username, rd.driver_password, idtr_for_delete);
		us_Methods.countTrips(buttonDetails, nextPageDriverTab); 
		numFromPageAsDriverBeforeDelet = us_Methods.numPage;
		numFromPageAsDriverAfterDelet = withoutPass.numFromPageAsDriverAfterDelet;

	}
}
