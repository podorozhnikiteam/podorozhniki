package com.epam.podorozhniki.us.US_1_1_2_8;

import static org.junit.Assert.assertFalse;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;

/*
 * Created by Zoja_Sharova
 */

public class TC_1128_1 extends BaseActions {

	// for driver only
	public TC_1128_1() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private CommonTests deletingTrips;
	private US_Methods us_Methods;

	public String queryDeletingAllDriverTrips;
	public String idtr;
	public String idtr_for_delete;

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;
	protected int numFromBaseAsDriverBeforeDelet;
	protected int numFromBaseAsDriverAfterDelet;
	protected int deletedTripFromBase;

	private static Logger log = Logger.getLogger(US_Methods.class);

	@FindBy(xpath = "//li[@id='li_driver']/a")
	protected WebElement asDriverTab;

	// check page after deleting trip
	@Test
	public void withoutPassOnPage() throws InterruptedException, SQLException {
		log.info("TEST STARTED");
		deletingTrips = new CommonTests();
		deletingTrips.deletingTripWithoutPassenger();
		numFromPageAsDriverBeforeDelet = deletingTrips.numFromPageAsDriverBeforeDelet;
		numFromPageAsDriverAfterDelet = deletingTrips.numFromPageAsDriverAfterDelet;
		deletingTrips.verifyNumberOfTripsOnthePage(
				numFromPageAsDriverBeforeDelet, numFromPageAsDriverAfterDelet);
		log.info("TEST FINISHED");
	}

	// check database after deleting
	@Test
	public void withoutPassInBase() throws InterruptedException, SQLException {
		log.info("TEST STARTED");
		deletingTrips = new CommonTests();
		deletingTrips.deletingTripWithoutPassenger();
		numFromBaseAsDriverBeforeDelet = deletingTrips.numFromBaseAsDriverBeforeDelet;
		numFromBaseAsDriverAfterDelet = deletingTrips.numFromBaseAsDriverAfterDelet;
		deletingTrips.verifyNumberOfTripsOnthePage(
				numFromBaseAsDriverBeforeDelet, numFromBaseAsDriverAfterDelet);
		log.info("TEST FINISHED");
	}

	// check correct deleting on the page
	@Test
	public void withoutPassCorrectDeleting() throws InterruptedException,
			SQLException {
		log.info("TEST STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		us_Methods = new US_Methods();
		us_Methods.addingTrips(rd.driver_username, rd.driver_password);
		idtr = us_Methods.idtr;
		idtr_for_delete = idtr;
		us_Methods.deletingTripAsDriver(rd.driver_username, rd.driver_password,
				idtr_for_delete);
		us_Methods.goToUserTab(rd.driver_username, rd.driver_password,
				asDriverTab);
		assertFalse(isElementPresent(By.xpath("//button[@idtr='" + idtr + "']")));
		log.info("TEST FINISHED");
	}

	// check correct deleting in database

	@Test
	public void withoutPassCorrectDeletinginBase() throws InterruptedException,
			SQLException {
		log.info("TEST STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		us_Methods = new US_Methods();
		us_Methods.addingTrips(rd.driver_username, rd.driver_password);
		idtr = us_Methods.idtr;
		idtr_for_delete = idtr;
		us_Methods.deletingTripAsDriver(rd.driver_username, rd.driver_password,
				idtr_for_delete);
		deletedTripFromBase = queryGetInt("select * from trip where id_trip = "
				+ idtr + "");
		Assert.assertEquals("Deleted trip is present in database",
				deletedTripFromBase, 0);
		log.info("TEST FINISHED");
	}

}
