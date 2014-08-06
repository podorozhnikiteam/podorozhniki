package com.epam.podorozhniki.us.US_1_1_2_8;

import static org.junit.Assert.assertFalse;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import com.epam.podorozhniki.ui.MyTripsPage;

public class TC_3_3 extends BaseActions {

	public TC_3_3() {
		PageFactory.initElements(Driver.getInstance(), this);
	}


	private TripWithPass tripWithPass;
	private MainPageBeforeLogin mainPageBeforeLogin;
	private MyTripsPage myTripsPage;
	private MainPageAfterLogin mainPageAfterLogin;
	private DriverService drService; 
	private PassengerService passService; 
	private ReadingDatafile rd; 
	
	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;
	
	public String idtr;

	private static Logger log = Logger.getLogger(TC_3_3.class);

	@Test
	public void withPassCorrectRemovingFromPassTab()
			throws InterruptedException, SQLException {
		log.info(" GET STARTED");
		rd = new ReadingDatafile(); 
		tripWithPass = new TripWithPass();
		tripWithPass.withPassAddingTripsWithoutCount();
		idtr = tripWithPass.idtr;
		log.info("idtr "+ idtr); 
		passService = new PassengerService();
		passService.goToAsPassengerTab(rd.passenger_username, rd.passenger_password);
		assertFalse(isElementPresent(By.xpath("//button[@idtr='" + idtr + "']")));
		log.info("Test passed succesfully");
	}
}