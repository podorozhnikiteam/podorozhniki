package com.epam.podorozhniki.us.US_001;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;

public class TC_001 {

	public static String driver_username;
	public static String driver_password;
	private static String query;

	public int numFromPage;
	public int numFromBase;

	private MainPageAfterLogin mainPageAfterLogin;
	private MainPageBeforeLogin mainPageBeforeLogin;

	private static Logger log = Logger.getLogger(TC_001.class);
	
	private By buttonJoin = By.xpath("//button[contains(text(),'Join')]");
	private By nextPage = By
			.xpath("//li[@class='active']/following-sibling::*[1]/self::li/a");

	@BeforeClass
	public static void setUp() throws InterruptedException {
		Driver.init();
		Driver.getInstance().manage().window().maximize();
		Driver.getInstance().get(
				"http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main");
		driver_username = System.getProperty("US1128.driver_login");
		driver_password = System.getProperty("US1128.driver_password");
		query = System.getProperty("US001.query");
	}

	@Test
	public void countRowsOnThePage() throws SQLException {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(driver_username, driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		numFromPage = mainPageAfterLogin.countTrips(buttonJoin, nextPage);
		System.out.println(numFromPage);
		mainPageAfterLogin.logout();
		mainPageAfterLogin.queryGetInt(query);
		numFromBase = mainPageAfterLogin.queryGetInt(query);
		log.info(numFromBase);
		log.info(numFromPage);
		assertEquals("Number of trips is not correct", numFromBase, numFromPage);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		Driver.tearDown();
	}
}
