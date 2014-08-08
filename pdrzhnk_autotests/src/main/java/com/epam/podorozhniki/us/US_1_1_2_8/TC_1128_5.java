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
	protected int tripStatus;

	private static Logger log = Logger.getLogger(TC_1128_2.class);

	@FindBy(xpath = "//li[@id='li_driver']/a")
	protected WebElement asDriverTab;

	@FindBy(xpath = "//li[@id='li_passenger']/a")
	protected WebElement asPassengerTab;

	// checking after deleting trip by passenger on passenger page 
	@Test
	public void passRemovesTripAcceptedStatusOnPassPage() throws InterruptedException,
			SQLException {
		log.info("DELETING TRIP BY PASSENGER GET STARTED");
		log.info("CHECKING OF PASSENGER PAGE STATUS ACCEPTED GET STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		commonTests.deletingTripWithPassengerByPass(rd.accepted);
		us_Methods = new US_Methods();
		us_Methods.getStatusFromPassPage(rd.passenger_username,
				rd.passenger_password, "cancelled");
		log.info("test passed succesfully");
	}

	@Test
	public void passRemovesTripDeniedStatusOnPassPage() throws InterruptedException,
			SQLException {
		log.info("CHECKING OF PASSENGER PAGE STATUS DENIED GET STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		commonTests.deletingTripWithPassengerByPass(rd.denied);
		us_Methods = new US_Methods();
		us_Methods.getStatusFromPassPage(rd.passenger_username,
				rd.passenger_password, "cancelled");
		log.info("test passed succesfully");
	}

	@Test
	public void passRemovesTripSubmittedStatusOnPassPage() throws InterruptedException,
			SQLException {
		log.info("CHECKING OF PASSENGER PAGE STATUS SUBMITTED GET STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		commonTests.deletingTripWithPassengerByPass(rd.submitted);
		us_Methods = new US_Methods();
		us_Methods.getStatusFromPassPage(rd.passenger_username,
				rd.passenger_password, "cancelled");
		log.info("test passed succesfully");
	}

	// checking after deleting trip by passenger on driver page
	@Test
	public void passRemovesTripAcceptedStatusCheckOnDriverPage()
			throws InterruptedException, SQLException {
		log.info("CHECKING OF DRIVER PAGE STATUS ACCEPTED GET STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		commonTests.deletingTripWithPassengerByPass(rd.accepted);
		us_Methods = new US_Methods();
		us_Methods.getStatusFromDriverPage(rd.driver_username,
				rd.driver_password, "cancelled");
		log.info("test passed succesfully");
	}

	@Test
	public void passRemovesTripDeniedStatusCheckOnDriverPage()
			throws InterruptedException, SQLException {
		log.info("CHECKING OF DRIVER PAGE STATUS DENIED GET STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		commonTests.deletingTripWithPassengerByPass(rd.denied);
		us_Methods = new US_Methods();
		us_Methods.getStatusFromDriverPage(rd.driver_username,
				rd.driver_password, "cancelled");
		log.info("test passed succesfully");
	}

	@Test
	public void passRemovesTripSubmittedStatusCheckOnDriverPage()
			throws InterruptedException, SQLException {
		log.info("CHECKING OF DRIVER PAGE STATUS SUBMITTED GET STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		commonTests.deletingTripWithPassengerByPass(rd.submitted);
		us_Methods = new US_Methods();
		us_Methods.getStatusFromDriverPage(rd.driver_username,
				rd.driver_password, "cancelled");
		log.info("test passed succesfully");
	}
	
	@Test
	public void passRemovesTripSubmittedStatusFromBase()
			throws InterruptedException, SQLException {
		log.info("CHECKING OF DATABASE STATUS SUBMITTED GET STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		commonTests.deletingTripWithPassengerByPass(rd.submitted);
		idtr_for_delete = commonTests.idtr_for_delete; 
		log.info("idtr_for_delete");
		new DBConnection(); 
		tripStatus = queryGetInt("select count(id_trip) as rowcout from trip where id_trip = "+idtr_for_delete+""); 
		Assert.assertEquals(tripStatus, 2);
		log.info("test passed succesfully");
	}

	@Test
	public void passRemovesTripDeniedStatusFromBase()
			throws InterruptedException, SQLException {
		log.info("CHECKING OF DATABASE STATUS DENIED GET STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		commonTests.deletingTripWithPassengerByPass(rd.denied);
		idtr_for_delete = commonTests.idtr_for_delete; 
		log.info("idtr_for_delete");
		new DBConnection(); 
		tripStatus = queryGetInt("select count(id_trip) as rowcout from trip where id_trip = "+idtr_for_delete+""); 
		Assert.assertEquals(tripStatus, 2);
		log.info("test passed succesfully");
	}
	
	@Test
	public void passRemovesTripAcceptedStatusFromBase()
			throws InterruptedException, SQLException {
		log.info("CHECKING OF DATABASE STATUS ACCEPTED GET STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		commonTests.deletingTripWithPassengerByPass(rd.accepted);
		idtr_for_delete = commonTests.idtr_for_delete; 
		log.info("idtr_for_delete");
		new DBConnection(); 
		tripStatus = queryGetInt("select count(id_trip) as rowcout from trip where id_trip = "+idtr_for_delete+""); 
		Assert.assertEquals(tripStatus, 2);
		log.info("test passed succesfully");
	}
}