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
import com.epam.podorozhniki.ui.MethodsPage;
import com.epam.podorozhniki.ui.MyTripsPage;

public class TC_3_1 extends MethodsPage {

	public TC_3_1() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private TripWihoutPass withoutPass;
	private ReadingDatafile rd;
	private DriverService drService; 

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;

	private static Logger log = Logger.getLogger(TC_3_1.class);

	@Test
	public void withoutPassCorrectRemoving() throws InterruptedException,
			SQLException {
		log.info(" GET STARTED");
		rd = new ReadingDatafile(); 
		withoutPass = new TripWihoutPass();
		withoutPass.addingTripsWithoutCount();
		String idtr = withoutPass.idtr;
		log.info("idtr "+ idtr); 
		drService = new DriverService();
		drService.goToAsDRiverTab(rd.driver_username, rd.driver_password);
		assertFalse(isElementPresent(By.xpath("//button[@idtr='" + idtr + "']")));
		log.info("Test passed succesfully");
	}
}