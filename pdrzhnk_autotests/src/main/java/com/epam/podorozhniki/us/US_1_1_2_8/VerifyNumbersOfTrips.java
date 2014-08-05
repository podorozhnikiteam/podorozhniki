package com.epam.podorozhniki.us.US_1_1_2_8;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MethodsPage;

public class VerifyNumbersOfTrips extends MethodsPage {

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPagePassBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;
	protected int numFromPagePassAfterDelet;
	protected int numFromDatabaseAsDriverBeforeDelet;
	protected int numFromDatabasePassBeforeDelet;
	protected int numFromDatabaseAsDriverAfterDelet;
	protected int numFromDatabasePassAfterDelet;

	public VerifyNumbersOfTrips() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private static Logger log = Logger.getLogger(MainPageAfterLogin.class);

	public void VerifyNumberOfTripsOnthePageAsDriver(
			int numFromPageAsDriverBeforeDelet,
			int numFromPageAsDriverAfterDelet) {
		Assert.assertEquals("ERROR ", numFromPageAsDriverBeforeDelet - 1,
				numFromPageAsDriverAfterDelet);
		log.info("OK: Verifying number of trips on the page as driver");
	}

	public void VerifyNumberOfTripsOnthePageAsPassenger(
			int numFromPagePassBeforeDelet, int numFromPagePassAfterDelet) {
		Assert.assertEquals("ERROR ", numFromPagePassBeforeDelet - 1,
				numFromPagePassAfterDelet);
		System.out.println("OK: Verifying number of trips on the page as passenger");

	}

	public void VerifyNumberOfTripsInTheDBAsDriver(
			int numFromDatabaseAsDriverBeforeDelet,
			int numFromDatabaseAsDriverAfterDelet) {
		Assert.assertEquals("ERROR ", numFromDatabaseAsDriverBeforeDelet - 1,
				numFromDatabaseAsDriverAfterDelet);
		System.out.println("OK: Verifying number of trips in the database as driver");
	}

	public void VerifyNumberOfTripsInTheDBAsPassenger(
			int numFromDatabasePassBeforeDelet,
			int numFromDatabasePassAfterDelet) {
		Assert.assertEquals("ERROR ", numFromDatabasePassBeforeDelet - 1,
				numFromDatabasePassAfterDelet);
		System.out.println("OK: Verifying number of trips in the database as passenger");
	}

	public MainPageAfterLogin goToMainPage() {
		return new MainPageAfterLogin();
	}
}
