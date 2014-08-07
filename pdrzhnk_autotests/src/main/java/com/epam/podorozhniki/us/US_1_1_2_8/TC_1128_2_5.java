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

public class TC_1128_2_5 extends BaseActions {

	public TC_1128_2_5() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private ReadingDatafile rd;
	private US_Methods us_Methods;

	public String idtr;
	public String idtr_for_delete;

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;

	private static Logger log = Logger.getLogger(TC_1128_2_5.class);
	
	@FindBy(xpath = "//li[@id='li_driver']/a")
	protected WebElement asDriverTab;

	// check correct deleting on the driver page with passenger  status accepted 
	@Test
	public void withPassCorrectRemovingFromDriverTab()
			throws InterruptedException, SQLException {
		log.info(" GET STARTED");
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
		us_Methods.goToUserTab(rd.driver_username, rd.driver_password, asDriverTab);
		assertFalse(isElementPresent(By.xpath("//button[@idtr='" + idtr + "']")));
		log.info("Test passed succesfully");

	}
}