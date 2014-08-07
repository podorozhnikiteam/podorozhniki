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

public class TC_1128_2_6 extends BaseActions {

	public TC_1128_2_6() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private ReadingDatafile rd;
	private US_Methods us_Methods;
	private DBConnection dbConnect;

	public String idtr;
	public String idtr_for_delete;
	public String query;

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;
	protected int deletedTripFromBase;

	private static Logger log = Logger.getLogger(TC_1128_2_5.class);

	@FindBy(xpath = "//li[@id='li_driver']/a")
	protected WebElement asDriverTab;

	// check correct deleting with passenger in the driver database status
	// accepted

	@Test
	public void withPassCorrectRemovingFromDriverTab()
			throws InterruptedException, SQLException {
		log.info(" GET STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		us_Methods = new US_Methods();
		us_Methods.addingTrips(rd.driver_username, rd.driver_password);
		idtr = us_Methods.idtr;
		log.info("user joinToTrip");
		us_Methods.joinToTrip(rd.passenger_username, rd.passenger_password,
				rd.from_address, rd.to_address, idtr);
		try {
			log.info("user goToUserTab");
			us_Methods.goToUserTab(rd.driver_username, rd.driver_password,
					asDriverTab);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("user goToPassStatus");
		us_Methods.goToPassStatus(rd.driver_username, rd.driver_password,
				idtr_for_delete, rd.accepted);
		log.info("user countTripsOnPage");
		idtr_for_delete = idtr;
		us_Methods.deletingTripAsDriver(rd.driver_username, rd.driver_password,
				idtr_for_delete);
		dbConnect = new DBConnection();
		log.info("idtr = " + idtr);
		deletedTripFromBase = queryGetInt("select * from trip where id_trip = "
				+ idtr + "");
		log.info("deletedTripFromBase =" + deletedTripFromBase);
		Assert.assertEquals("Deleted trip is present in database",
				deletedTripFromBase, 0);
		log.info("Test passed succesfully");
	}
}
