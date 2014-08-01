package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;

public class RunnerUS_1128 {
	private MainPageAfterLogin mainPageAfterLogin;
	private MainPageBeforeLogin mainPageBeforeLogin;

	private TripRemoving tripRemoving;
	private CountTripOnPage countTripOnPAge;
	private VerifyNumbersOfTrips verifyNumbersOfTrips;
	private PreconditionsPage preconditionsPage;
	private PostconditionsPage postconditionsPage;
	private CountTripsInDatabase countTripsInDatabase;

	public String from_address = "Киев, ул. Комарова, 12";
	public String to_address = "Киев, ул. Верхний Вал, 57";
	public String driver_username = "creditnew";
	public String driver_password = "1234567";
	public String idtr = "";

	protected int numFromPageBeforeDeleting;
	protected int numFromPageAfterDeleting;

	private static Logger log = Logger.getLogger(MainPageAfterLogin.class);

	@Before
	public void setUp() throws InterruptedException {
		Driver.init();
		Driver.getInstance().manage().window().maximize();
		Driver.getInstance().get(
				"http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main");
		preconditionsPage = PageFactory.initElements(Driver.getInstance(),
				PreconditionsPage.class);
		preconditionsPage.addingTripsAsDriver();
		preconditionsPage.addingToTripAsPassenger();
		mainPageAfterLogin = preconditionsPage.goToMainPageAfterLogin();
		mainPageAfterLogin.logout();
	}

	// для TESTNG разбить на тесты - проверка до удаления на странице и в базе, проверка после и сравнение , три теста 
	@Test
	public void Runner() throws SQLException {
		countTripOnPAge = PageFactory.initElements(Driver.getInstance(),
				CountTripOnPage.class);
		countTripOnPAge.countingTripsBeforeDeletingAsDriver();
		countTripOnPAge.countingTripsBeforeDeletingAsPassenger();
		countTripsInDatabase = countTripOnPAge.goToDB();
		countTripsInDatabase.countingTripsBeforeDeletingAsDriver();
		countTripsInDatabase.countingTripsBeforeDeletingAsPassenger();
		tripRemoving = countTripsInDatabase.goToTripRemovingPage();
		tripRemoving.removingTripAsDriver();
		countTripOnPAge = tripRemoving.goTocountTripPage();
		countTripOnPAge.countingTripsAsDriverAFterDeleting();
		countTripOnPAge.countingTripsAsPassengerAFterDeleting();
		countTripsInDatabase = countTripOnPAge.goToDB();
		countTripsInDatabase.countingTripsAfterDeletingAsDriver();
		countTripsInDatabase.countingTripsAfterDeletingAsPassenger();
		verifyNumbersOfTrips = countTripsInDatabase.goToVerifyNumbersOfTrips();
		verifyNumbersOfTrips.VerifyNumberOfTripsOnthePageAsDriver();
		verifyNumbersOfTrips.VerifyNumberOfTripsInTheDBAsPassenger();
		verifyNumbersOfTrips.VerifyNumberOfTripsInTheDBAsDriver();
		verifyNumbersOfTrips.VerifyNumberOfTripsInTheDBAsPassenger();
		mainPageAfterLogin = verifyNumbersOfTrips.goToMainPage();
		mainPageBeforeLogin = mainPageAfterLogin.logout();
	}

	@After
	public void afterTest() throws SQLException {
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		postconditionsPage = mainPageAfterLogin.goPostconditionsPage();
		mainPageAfterLogin = postconditionsPage.DeletingTripsFromDB();
		mainPageBeforeLogin = mainPageAfterLogin.logout();
		Driver.tearDown();
	}

}
