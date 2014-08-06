package com.epam.podorozhniki.us.US_01_1_1_3;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import com.epam.podorozhniki.ui.MyTripsPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yana_Velbovets on 7/30/2014.
 */
public class US_01_1_1_3_Steps {

    private MainPageAfterLogin mainPageAfterLogin;
    private MainPageBeforeLogin mainPageBeforeLogin;
    private MyTripsPage myTripsPage;
    private int ammOfTripsDB;
    private int ammOfTripsPage;

    @Before
    public void driver() {
        Driver.init();
        Driver.getInstance().manage().window().maximize();
        Driver.getInstance().get("http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main");
    }

    @After
    public void closeBrowser() {
        Driver.tearDown();
    }

    @Test
    public void checkAmountOfTrips() {
        mainPageBeforeLogin = new MainPageBeforeLogin();
        mainPageBeforeLogin.assertFieldsArePresent();
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("driverLoginName"), System.getProperty("driverLoginPassword"));
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        myTripsPage.gotoAsDriverTab();
        try {
            ammOfTripsDB = myTripsPage.countRecordsInColumnFromDb(System.getProperty("query"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ammOfTripsPage = myTripsPage.countTripsInColume(Integer.parseInt(System.getProperty("countAmountOfTripsInColumn")));
        assertEquals(ammOfTripsDB, ammOfTripsPage);
    }
}
