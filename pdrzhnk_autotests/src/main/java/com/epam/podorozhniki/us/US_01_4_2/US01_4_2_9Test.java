package com.epam.podorozhniki.us.US_01_4_2;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MyTripsPage;

public class US01_4_2_9Test {
	
	final int NUMBER_OF_COLUME = 2;
	final int QTY_OF_TRIPS = 3;

	@Test(groups = { "SecondSuit" })
	public void functionalityOfFilterToTest() throws InterruptedException {
		MainPageAfterLogin mainPage = new MainPageAfterLogin();
		MyTripsPage myRoutsPage = mainPage.goToMyTripsPage();
		myRoutsPage.gotoDriverCalendar();
		myRoutsPage.selectAllInToFilter();
		
		int expectedResultOfAllSelectionsInToFilter = QTY_OF_TRIPS;
		int actual = myRoutsPage.countTripsInColume(NUMBER_OF_COLUME);
		
		Assert.assertEquals(actual,	expectedResultOfAllSelectionsInToFilter,
				"The results in colume \"To\" on the page and expected from paticular user is not same!");
		Reporter.log("Done", 2, true);
	}
}
