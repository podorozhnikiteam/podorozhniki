package com.epam.podorozhniki.us.US_02_4_2;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.AddTripPage;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import com.epam.podorozhniki.ui.MyTripsPage;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Viktoriia_Ishchuk on 7/29/2014.
 */
public class autoTest_US_2_4_2 {

    static final Logger logger = Logger.getLogger(autoTest_US_2_4_2.class);

    private MainPageBeforeLogin mainPageBeforeLogin;
    private MainPageAfterLogin mainPageAfterLogin;
    private MyTripsPage myTripsPage;
    private AddTripPage addTripPage;

    //Login Info
    public String driverLoginName = "driverBot";
    public String driverLoginPassword = "123456";
    public String passengerLoginName = "travelerBot";
    public String passengerLoginPassword = "123456";
    public String fromAddress = "Киев Кудряшова 18";
    public String toAddress = "Киев Регенераторная 4";
    public String idtr = ""; //Trip ID (database)- will generate, when trip is created.

    @Before
    public void setUp() throws InterruptedException {
        logger.info("BEFORE TEST:");
        logger.info("OPEN FIREFOX DRIVER");
        Driver.init();
        Driver.getInstance().manage().window().maximize();
        logger.info("OPEN MAIN PAGE: http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main.");
        Driver.getInstance().get("http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main");
        mainPageBeforeLogin = new MainPageBeforeLogin();
        logger.info("DRIVER LOGIN INTO THE SYSTEM.");
        mainPageBeforeLogin.enterLoginAndPass(driverLoginName, driverLoginPassword);
        logger.info("OPEN MY TRIPS TAB.");
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        logger.info("OPEN ADD ROUTE PAGE.");
        addTripPage = myTripsPage.gotoAddTripPage();
        logger.info("DRIVER CREATE NEW TRIP FOR PASSENGER.");
        myTripsPage = addTripPage.addTrip(fromAddress, toAddress);
        logger.info("OPEN MY TRIPS PAGE");
        logger.info("GET NEW TRIP ID.");
        idtr = myTripsPage.getTripId();
        logger.info("Trip ID = " + idtr);
        mainPageAfterLogin = myTripsPage.gotoMainPage();
        logger.info("DRIVER LOGOUT FROM THE SYSTEM");
        mainPageBeforeLogin = mainPageAfterLogin.logout();
    }

    @Test
    public void US11_SUBMITTED() {
        logger.info("START AUTOTEST US11_SUBMITTED.");
        logger.info("US02.4.2 - TRAVELLER'S CALENDAR. TC02.4.2.1 - SUBMITTED");
        logger.info("PASSENGER LOGIN INTO THE SYSTEM.");
        mainPageBeforeLogin.enterLoginAndPass(passengerLoginName, passengerLoginPassword);
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        logger.info("PASSENGER FIND CURRENT DRIVER'S TRIP AND JOIN IT.");
        mainPageAfterLogin.joinTripByPassenger(fromAddress, toAddress, idtr);
        logger.info("PASSENGER OPEN 'My Trips' TAB.");
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        logger.info("PASSENGER OPEN 'Passenger Calendar' Tab.");
        myTripsPage.gotoPassengerCalendar();
        logger.info("PASSENGER VERIFY STATUS OF TRIP BY 'Day' VIEW.");
        myTripsPage.verifyTripStatusAsPassengerByDayFilter("SUBMITTED");
        logger.info("PASSENGER VERIFY STATUS OF TRIP BY 'Week' VIEW.");
        myTripsPage.verifyTripStatusAsPassengerByWeekFilter("SUBMITTED");
        logger.info("PASSENGER VERIFY STATUS OF TRIP BY 'Month' VIEW.");
        myTripsPage.verifyTripStatusAsPassengerByMonthFilter("SUBMITTED");
        logger.info("PASSENGER VERIFY STATUS OF TRIP BY 'Year' VIEW.");
        myTripsPage.verifyTripStatusAsPassengerByYearFilter("SUBMITTED");
        logger.info("PASSENGER OPEN MAIN PAGE.");
        mainPageAfterLogin = myTripsPage.gotoMainPage();
    }

    @After
    public void tearDown() {
        logger.info("AFTER TEST:");
        logger.info("PASSENGER LOGOUT FROM THE SYSTEM.");
        mainPageBeforeLogin = mainPageAfterLogin.logout();
        logger.info("DRIVER LOGIN INTO THE SYSTEM");
        mainPageBeforeLogin.enterLoginAndPass(driverLoginName,driverLoginPassword);
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        logger.info("OPEN MYTRIPS PAGE");
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        logger.info("DRIVER REMOVE CURRENT TRIP.");
        myTripsPage.removePassengerTrip();
        mainPageAfterLogin = myTripsPage.gotoMainPage();
        logger.info("DRIVER LOGOUT FROM THE SYSTEM.");
        mainPageBeforeLogin = mainPageAfterLogin.logout();
        logger.info("CLOSE FIREFOX DRIVER.");
        Driver.tearDown();
        logger.info("ALL SCREENSHOTS AND LOG FILE CAN BE FOUND BY PATH: D:\\Viktoriia_Ishchuk\\gitProjects\\podorozhniki_us11\\screenshots\\");
    }

}
