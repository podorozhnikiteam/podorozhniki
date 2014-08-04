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

	public VerifyNumbersOfTrips() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private static Logger log = Logger.getLogger(MainPageAfterLogin.class);

	public void VerifyNumberOfTripsOnthePageAsDriver(
			int numFromPageAsDriverBeforeDelet,
			int numFromPageAsDriverAfterDelet) {
		try {
			assertEquals(numFromPageAsDriverBeforeDelet - 1,
					numFromPageAsDriverAfterDelet);
			System.out.println("VerifyNumberOfTripsOnthePageAsDriver- OK");
		} catch (AssertionError e1) {
			e1.printStackTrace();
			System.out
					.println("ERROR VerifyNumberOfTripsOnthePageAsDriver");
		}

	}

	public void VerifyNumberOfTripsOnthePageAsPassenger(
			int numFromPagePassBeforeDelet, int numFromPagePassAfterDelet) {
		try {
			assertEquals(numFromPagePassBeforeDelet - 1,
					numFromPagePassAfterDelet);
			System.out.println("VerifyNumberOfTripsOnthePageAsPassenger- OK");
		} catch (AssertionError e1) {
			e1.printStackTrace();
			System.out
					.println("ERROR VerifyNumberOfTripsOnthePageAsPassenger");
		}

	}

	public void VerifyNumberOfTripsInTheDBAsDriver(
			int numFromDatabaseAsDriverBeforeDelet,
			int numFromDatabaseAsDriverAfterDelet) {
		try {
			assertEquals(numFromDatabaseAsDriverBeforeDelet - 1,
					numFromDatabaseAsDriverAfterDelet);
			System.out.println("VerifyNumberOfTripsInTheDBAsDriver- OK");
		} catch (AssertionError e1) {
			e1.printStackTrace();
			System.out.println("VerifyNumberOfTripsInTheDBAsDriver- ERROR");
		}

	}

	public void VerifyNumberOfTripsInTheDBAsPassenger(
			int numFromDatabasePassBeforeDelet,
			int numFromDatabasePassAfterDelet) {
		try {
			assertEquals(numFromDatabasePassBeforeDelet - 1,
					numFromDatabasePassAfterDelet);
			System.out.println("VerifyNumberOfTripsInTheDBAsPassenger- OK");
		} catch (AssertionError e1) {
			e1.printStackTrace();
			System.out.println("VerifyNumberOfTripsInTheDBAsPassenger- ERROR");
		}

	}

	public MainPageAfterLogin goToMainPage() {
		return new MainPageAfterLogin();
	}
}
