package com.epam.podorozhniki.us.US_1_1_2_8;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MethodsPage;

public class VerifyNumbersOfTrips extends MethodsPage {

	public String from_address = "Киев, ул. Комарова, 12";
	public String to_address = "Киев, ул. Верхний Вал, 57";
	public String driver_username = "creditnew";
	public String driver_password = "1234567";
	public String idtr = "";

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPagePassBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;
	protected int numFromPagePassAfterDelet;
	protected int numFromDatabaseAsDriverBeforeDelet;
	protected int numFromDatabasePassBeforeDelet;
	protected int numFromDatabaseAsDriverAfterDelet;
	protected int numFromDatabasePassAfterDelet;

	private CountTripOnPage countTripOnPage;
	private CountTripsInDatabase countTripsInDatabase;

	public VerifyNumbersOfTrips() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private static Logger log = Logger.getLogger(MainPageAfterLogin.class);

	public void VerifyNumberOfTripsOnthePageAsDriver() {
		countTripOnPage = PageFactory.initElements(Driver.getInstance(),
				CountTripOnPage.class);
		numFromPageAsDriverBeforeDelet = countTripOnPage.numFromPageAsDriverBeforeDelet;
		numFromPageAsDriverAfterDelet = countTripOnPage.numFromPageAsDriverAfterDelet;

		try {
			assertEquals(
					"Number of trips on driver page before deleting is not equal of number of trips on driver page after deleting",
					numFromPageAsDriverBeforeDelet,
					numFromPageAsDriverAfterDelet);
		} catch (AssertionError e1) {
			e1.printStackTrace();
		}

	}

	public void VerifyNumberOfTripsOnthePageAsPassenger() {
		countTripOnPage = PageFactory.initElements(Driver.getInstance(),
				CountTripOnPage.class);
		numFromPagePassBeforeDelet = countTripOnPage.numFromPageAsPassengerBeforeDelet;
		numFromPagePassAfterDelet = countTripOnPage.numFromPageAsPassengerAfterDelet;

		try {
			assertEquals(
					"Number of trips on passenger page before deleting is not equal of number of trips on passenger page after deleting",
					numFromPagePassBeforeDelet, numFromPagePassAfterDelet);
		} catch (AssertionError e1) {
			e1.printStackTrace();
		}

	}

	public void VerifyNumberOfTripsInTheDBAsDriver() {
		countTripsInDatabase = PageFactory.initElements(Driver.getInstance(),
				CountTripsInDatabase.class);
		numFromDatabaseAsDriverBeforeDelet = countTripsInDatabase.numFromBaseAsDriverBeforeDelet;
		numFromDatabaseAsDriverAfterDelet = countTripsInDatabase.numFromBaseAsDriverAfterDelet;
		try {
			assertEquals(
					"Number of driver's trips in the DB before deleting is not equal of driver's trips in the DB after deleting",
					numFromDatabaseAsDriverBeforeDelet,
					numFromDatabaseAsDriverAfterDelet);
		} catch (AssertionError e1) {
			e1.printStackTrace();
		}

	}

	public void VerifyNumberOfTripsInTheDBAsPassenger() {
		countTripsInDatabase = PageFactory.initElements(Driver.getInstance(),
				CountTripsInDatabase.class);
		numFromDatabasePassBeforeDelet = countTripsInDatabase.numFromBaseAsPassBeforeDelet;
		numFromDatabasePassAfterDelet = countTripsInDatabase.numFromBaseAsPassAfterDelet;
		try {
			assertEquals(
					"Number of passenger's trips in the DB before deleting is not equal of passenger's trips in the DB after deleting",
					numFromDatabasePassBeforeDelet,
					numFromDatabasePassAfterDelet);
		} catch (AssertionError e1) {
			e1.printStackTrace();
		}

	}

	public MainPageAfterLogin goToMainPage() {
		return new MainPageAfterLogin();
	}
}
