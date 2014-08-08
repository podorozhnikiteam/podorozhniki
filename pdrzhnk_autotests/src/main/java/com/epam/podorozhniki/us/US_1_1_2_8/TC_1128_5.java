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

public class TC_1128_5 extends BaseActions {

	// deleting trip by passenger

	public TC_1128_5() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private CommonTests commonTests;
	private US_Methods us_Methods;
	private ReadingDatafile rd;

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

	// checking after deleting trip by passenger on passenger trip
	@Test
	public void passRemovesTripAcceptedStatus() throws InterruptedException,
			SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		commonTests.deletingTripWithPassengerByPass(rd.accepted);
		us_Methods = new US_Methods();
		us_Methods.getStatusFromPassPage(rd.passenger_username,
				rd.passenger_password, "cancelled", asPassengerTab);
		log.info("test passed succesfully");
	}

	@Test
	public void passRemovesTripDeniedStatus() throws InterruptedException,
			SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		commonTests.deletingTripWithPassengerByPass(rd.denied);
		us_Methods.getStatusFromPassPage(rd.passenger_username,
				rd.passenger_password, "cancelled", asPassengerTab);
		log.info("test passed succesfully");
	}

	@Test
	public void passRemovesTripSubmittedStatus() throws InterruptedException,
			SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		commonTests.deletingTripWithPassengerByPass(rd.submitted);
		us_Methods.getStatusFromPassPage(rd.passenger_username,
				rd.passenger_password, "cancelled", asPassengerTab);
		log.info("test passed succesfully");
	}

	// checking after deleting trip by passenger on driver trip
	@Test
	public void passRemovesTripAcceptedStatusCheckOnDriverPage()
			throws InterruptedException, SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		commonTests.deletingTripWithPassengerByPass(rd.accepted);
		us_Methods = new US_Methods();
		us_Methods.getStatusFromDriverPage(rd.driver_username,
				rd.driver_password, "cancelled", asDriverTab);
		log.info("test passed succesfully");
	}

}