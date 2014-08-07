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

public class TC_1128_1_4 extends BaseActions {

	public TC_1128_1_4() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private ReadingDatafile rd;
	private US_Methods us_Methods;

	public String idtr;
	public String idtr_for_delete;

	protected int deletedTripFromBase; 

	private static Logger log = Logger.getLogger(TC_1128_1_4.class);
	
	@FindBy(xpath = "//li[@id='li_driver']/a")
	protected WebElement asDriverTab;
 
	// check correct deleting in database without passenger  
	
	@Test
	public void withoutPassCorrectDeletinginBase() throws InterruptedException,
			SQLException {

		log.info(" GET STARTED");
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
		log.info("deletedTripFromBase =" + deletedTripFromBase);
		Assert.assertEquals("Deleted trip is present in database",
				deletedTripFromBase, 0);
		log.info("Test passed succesfully");
	}
}