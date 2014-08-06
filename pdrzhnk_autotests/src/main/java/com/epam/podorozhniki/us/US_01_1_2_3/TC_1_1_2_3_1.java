package com.epam.podorozhniki.us.US_01_1_2_3;

import com.epam.podorozhniki.core.BaseTest;
import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.AddTripPage;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import com.epam.podorozhniki.ui.MyTripsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_1_1_2_3_1 extends BaseTest{

    private MainPageBeforeLogin mainPageBeforeLogin;
    private MainPageAfterLogin mainPageAfterLogin;
    private MyTripsPage myRoutesPage;
    private AddTripPage addTripPage;

    @BeforeMethod
    public void login() {
        Driver.getInstance().get("http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main");
        mainPageBeforeLogin = new MainPageBeforeLogin();
        mainPageBeforeLogin.enterLoginAndPass("igor", "123");
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
    }

    @Test
    public void addTrip_withFourSeats_fourFreeSeats() {
        mainPageAfterLogin = new MainPageAfterLogin();
        myRoutesPage = mainPageAfterLogin.goToMyTripsPage();
        addTripPage = myRoutesPage.gotoAddTripPage();

        addTripPage.setFromField("Жилянская, 75");
        addTripPage.setToField("Кудряшова, 18");
        addTripPage.clickOnBuildOnMapButton();
        addTripPage.setPassengerSeats(4);
        addTripPage.clickOnCreateTripBtn();
        addTripPage.acceptAlertMessage();
    }

}
