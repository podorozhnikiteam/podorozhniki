package com.epam.podorozhniki.us.US_01_1_2_3;

import com.epam.podorozhniki.core.BaseTest;
import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.AddTripPage;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import com.epam.podorozhniki.ui.MyTripsPage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_1_1_2_3_1 extends BaseTest{

    private MainPageBeforeLogin mainPageBeforeLogin;
    private MainPageAfterLogin mainPageAfterLogin;
    private MyTripsPage myTripsPage;
    private AddTripPage addTripPage;
    private Logger log = Logger.getLogger(TC_1_1_2_3_1.class);

    @BeforeMethod
    public void login() {
        Driver.getInstance().manage().window().maximize();
        Driver.getInstance().get(System.getProperty("baseUrl"));
        log.info("Opening home url");
        mainPageBeforeLogin = new MainPageBeforeLogin();
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("US16.login"), System.getProperty("US16.password"));
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        log.info("Logging in");
    }

    @Test
    public void addTrip_withFourSeats_fourFreeSeats() {
        String expected = "4";
        mainPageAfterLogin = new MainPageAfterLogin();
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        log.info("Opening MyTrips page");
        addTripPage = myTripsPage.gotoAddTripPage();
        log.info("Opening AddTrip page");

        addTripPage.setFromField("Жилянская, 75");
        addTripPage.setToField("Кудряшова, 18");
        addTripPage.clickOnBuildOnMapButton();
        addTripPage.setPassengerSeats(4);
        addTripPage.clickOnCreateTripBtn();
        log.info("Creating trip");

        Assert.assertEquals("Trip hasn't passed validation!", addTripPage.getAlertMessage());
    }

    @Test
    public void addTrip_withZeroSeats_getError() {
        mainPageAfterLogin = new MainPageAfterLogin();
        log.info("Opening MyTrips page");
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        addTripPage = myTripsPage.gotoAddTripPage();
        log.info("Opening AddTrip page");

        addTripPage.setFromField("Жилянская, 75");
        addTripPage.setToField("Кудряшова, 18");
        addTripPage.clickOnBuildOnMapButton();
        addTripPage.setPassengerSeats(0);
        addTripPage.clickOnCreateTripBtn();
        log.info("Creating trip");

        Assert.assertEquals("Trip hasn't passed validation!", addTripPage.getAlertMessage());
    }

    @Test
    public void addTrip_withFiveSeats_getError() {
        mainPageAfterLogin = new MainPageAfterLogin();
        log.info("Opening MyTrips page");
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        addTripPage = myTripsPage.gotoAddTripPage();
        log.info("Opening AddTrip page");

        addTripPage.setFromField("Жилянская, 75");
        addTripPage.setToField("Кудряшова, 18");
        addTripPage.clickOnBuildOnMapButton();
        addTripPage.setPassengerSeats(0);
        addTripPage.clickOnCreateTripBtn();
        log.info("Creating trip");

        Assert.assertEquals("Trip hasn't passed validation!", addTripPage.getAlertMessage());
    }

    @Test
    public void addTrip_withThreeSeats_threeFreeSeats() {
        String expected = "3";
        mainPageAfterLogin = new MainPageAfterLogin();
        log.info("Opening MyTrips page");
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        addTripPage = myTripsPage.gotoAddTripPage();
        log.info("Opening AddTrip page");

        addTripPage.setFromField("Жилянская, 75");
        addTripPage.setToField("Кудряшова, 18");
        addTripPage.clickOnBuildOnMapButton();
        addTripPage.setPassengerSeats(3);
        addTripPage.clickOnCreateTripBtn();
        addTripPage.acceptAlertMessage();
        myTripsPage = addTripPage.clickOnMyTripsPage();
        log.info("Creating trip");

        Assert.assertEquals(expected, myTripsPage.getTotalSeatsNumber(3));
    }
}
