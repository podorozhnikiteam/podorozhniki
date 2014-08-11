package com.epam.podorozhniki.us.US_1_1_2_8;

import static org.junit.Assert.assertFalse;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;

/*
 * Created by Zoja_Sharova
 */

public class TC_1128_2 extends BaseActions {

	// deleting trip with passenger status accepted

	public TC_1128_2() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private CommonTests commonTests;
	private US_Methods us_Methods;

	public String idtr;
	public String idtr_for_delete;

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;
	protected int numFromBaseAsDriverBeforeDelet;
	protected int numFromBaseAsDriverAfterDelet;
	protected int numFromPageAsPassBeforeDelet;
	protected int numFromPageAsPassAfterDelet;
	protected int numFromBaseAsPassBeforeDelet;
	protected int numFromBaseAsPassAfterDelet;
	protected int deletedTripFromBase;

	private static Logger log = Logger.getLogger(TC_1128_2.class);

	@FindBy(xpath = "//li[@id='li_driver']/a")
	protected WebElement asDriverTab;

	@FindBy(xpath = "//li[@id='li_passenger']/a")
	protected WebElement asPassengerTab;

	// check driver page after deleting trip
	@Test
	public void withPassAcceptedStatusAsDriverOnPage()
			throws InterruptedException, SQLException {
		log.info("DELETING TRIP WITH PASSENGER STATUS ACCEPTED GET STARTED");
		log.info(" CHECKING OF DRIVER PAGE GET STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		try {
			commonTests.deletingTripWithPassenger(rd.accepted, asDriverTab);
			numFromPageAsDriverBeforeDelet = commonTests.numFromPageAsDriverBeforeDelet;
			numFromPageAsDriverAfterDelet = commonTests.numFromPageAsDriverAfterDelet;
			commonTests.verifyNumberOfTripsOnthePage(
					numFromPageAsDriverBeforeDelet, numFromPageAsDriverAfterDelet);
			log.info("Test passed succesfully");
		} catch (Throwable e) {
			getCurrentScreenshots(rd.filePath, "On_Page_As_Driver"); 
		}
	}

	// check driver database after deleting trip
	@Test
	public void withPassAcceptedStatusAsDriverInBase()
			throws InterruptedException, SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		log.info("CHECKING OF DRIVER'INFO IN DATABASE GET STARTED");
		commonTests = new CommonTests();
		try {
			commonTests.deletingTripWithPassenger(rd.accepted, asDriverTab);
			numFromBaseAsDriverBeforeDelet = commonTests.numFromBaseAsDriverBeforeDelet;
			numFromBaseAsDriverAfterDelet = commonTests.numFromBaseAsDriverAfterDelet;
			commonTests.verifyNumberOfTripsOnthePage(
					numFromBaseAsDriverBeforeDelet, numFromBaseAsDriverAfterDelet);
			log.info("Test passed succesfully");
		} catch (Throwable e) {
			getCurrentScreenshots(rd.filePath, "In_Base_As_SDriver"); 
		}
	}

	// check passenger page after deleting trip
	@Test
	public void withPassAcceptedStatusAsPassOnPage()
			throws InterruptedException, SQLException {
		log.info("CHECKING OF PASSENGER PAGE GET STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		try {
			commonTests.deletingTripWithPassenger(rd.accepted, asPassengerTab);
			numFromPageAsPassBeforeDelet = commonTests.numFromPageAsPassBeforeDelet;
			numFromPageAsPassAfterDelet = commonTests.numFromPageAsPassAfterDelet;
			commonTests.verifyNumberOfTripsOnthePage(numFromPageAsPassBeforeDelet,
					numFromPageAsPassAfterDelet);
			log.info("Test passed succesfully");
		} catch (Throwable e) {
			getCurrentScreenshots(rd.filePath, "as_Pass_On_Page"); 
		}
	}

	// check passenger database after deleting trip
	@Test
	public void withPassAcceptedStatusAsPassInBase()
			throws InterruptedException, SQLException {
		log.info("CHECKING OF PASSENGER'S INFO IN DATABASE GET STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		try {
			commonTests.deletingTripWithPassenger(rd.accepted, asPassengerTab);
			numFromBaseAsPassBeforeDelet = commonTests.numFromBaseAsPassBeforeDelet;
			numFromBaseAsPassAfterDelet = commonTests.numFromBaseAsPassAfterDelet;
			commonTests.verifyNumberOfTripsOnthePage(numFromBaseAsPassBeforeDelet,
					numFromBaseAsPassAfterDelet);
			log.info("Test passed succesfully");
		} catch (Throwable e) {
			getCurrentScreenshots(rd.filePath, "as_Pass_In_Base");
		}
	}

	// check correct deleting on the driver page
	@Test
	public void withPassAcceptedStatusCorrectRemovingFromDriverTab()
			throws InterruptedException, SQLException {
		log.info("CHECKING OF CORRECT DELETING ON DRIVER'S PAGE GET STARTED");
		try { 
		rd = new ReadingDatafile();
		rd.readingDataFile();
		us_Methods = new US_Methods();
		us_Methods.addingTrips(rd.driver_username, rd.driver_password);
		idtr = us_Methods.idtr;
		us_Methods.joinToTrip(rd.passenger_username, rd.passenger_password,
				rd.from_address, rd.to_address, idtr);
		idtr_for_delete = idtr;
		us_Methods.deletingTripAsDriver(rd.driver_username, rd.driver_password,
				idtr_for_delete);
		us_Methods.goToUserTab(rd.driver_username, rd.driver_password,
				asDriverTab);
		assertFalse(isElementPresent(By.xpath("//button[@idtr='" + idtr + "']")));
		log.info("Test passed succesfully");
			} catch (Throwable e) {
				getCurrentScreenshots(rd.filePath, "correct_Deleting_On_Driver_Page");
			}
	}

	// check correct deleting on the passenger page
	@Test
	public void withPassAcceptedStatusCorrectRemovingFromPassTab()
			throws InterruptedException, SQLException {
		log.info(" CHECKING OF CORRECT DELETING ON PASSENGER'S PAGE  GET STARTED");
		commonTests = new CommonTests();
		try { 
		us_Methods = commonTests.withPassCorrectRemovingFromTab(rd.accepted);
		us_Methods.goToUserTab(rd.passenger_username, rd.passenger_password,
				asPassengerTab);
		assertFalse(isElementPresent(By.xpath("//button[@idtr='" + idtr + "']")));
		log.info("Test passed succesfully");
			} catch (Throwable e) {
				getCurrentScreenshots(rd.filePath, "correct_Deleting_On_Pass_Page");
			}
	}

	// check correct deleting of trip in database
	@Test
	public void withPassAcceptedStatusCorrectRemovingFromDataBase()
			throws InterruptedException, SQLException {
		log.info("CHECKING OF CORRECT DELETING FROM DATABASE GET STARTED");
		commonTests = new CommonTests();
		commonTests.withPassCorrectRemovingFromDataBase(rd.accepted);
	}
}
