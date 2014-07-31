package com.epam.podorozhniki.us.US_1_1_2_8;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.AddTripPage;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import com.epam.podorozhniki.ui.MethodsPage;
import com.epam.podorozhniki.ui.MyTripsPage;

public class countTripAsDriver {
	private MainPageAfterLogin mainPageAfterLogin;
	private MainPageBeforeLogin mainPageBeforeLogin;

	private MyTripsPage myTripsPage;
	private AddTripPage addTripPage;

	public String from_address = "Киев, ул. Комарова, 12";
	public String to_address = "Киев, ул. Верхний Вал, 57";
	public String driver_username = "creditnew";
	public String driver_password = "1234567";
	public String idtr = "";

	protected int numFromPageBeforeDeleting;
	protected int numFromPageAfterDeleting;

	private static Logger log = Logger.getLogger(MainPageAfterLogin.class);

	private By details_Button = By.xpath("//a[contains(text(),'Details')]");
	private By nextPage = By.xpath("//div[@id='routeResults']//li[3]/a");
	
	public countTripAsDriver() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	// count trips before deleting
	public void countingTripsBeforeDeleting() {
		log.info("countTripAsDriver");
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.countTripsOnThePage(details_Button, nextPage);
		MethodsPage.numFromPage = numFromPageBeforeDeleting;
		log.info("There are " + numFromPageBeforeDeleting
				+ "on the AsDriverPage before deleting");
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	// count trips after deleting
	public void countingTripsAFterDeleting() {
		log.info("countNumberOfTripsAfterDeletingAsDriver");
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.countTripsOnThePage(details_Button, nextPage);
		MethodsPage.numFromPage = numFromPageAfterDeleting;
		log.info("There are " + numFromPageAfterDeleting
				+ "on the AsDriverPage before deleting");
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageBeforeLogin = mainPageAfterLogin.logout();
	}

}
