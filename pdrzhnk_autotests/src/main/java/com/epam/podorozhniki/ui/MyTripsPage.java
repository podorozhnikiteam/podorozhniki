package com.epam.podorozhniki.ui;

import com.epam.podorozhniki.core.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Viktoriia_Ishchuk on 7/25/2014.
 */
public class MyTripsPage extends MethodsPage {
    public MyTripsPage() {
        PageFactory.initElements(Driver.getInstance(), this);
    }

    @FindBy(xpath = "//div[@class='logo-container']/a")
    public WebElement mainPageLink;

    @FindBy(id = "my_trips")
    public WebElement myTripsLink;

    //Tabs
    @FindBy(xpath = "//li[@id='li_passenger']/a")
    protected WebElement asPassengerTab;

    @FindBy(xpath = "//li[@id='li_driver']/a")
    protected WebElement asDriverTab;

    @FindBy(xpath = "//li]@id='li_driverCalendar']/a")
    protected WebElement driverCalendarTab;

    @FindBy(xpath = "//li[@id='li_passengerCalendar']/a")
    protected WebElement passengerCalendarTab;

    //As Passenger Tab
    @FindBy(xpath = "//div[@id='PassengerTrips']/div/table/tbody/tr/td[5]")
    private WebElement asPassengerStatusSubmitted;

    //As Driver Tab
    @FindBy(xpath = "//div[@class ='control-group span8']/a")
    protected WebElement asDriverAddTripButton;

    @FindBy(xpath = "//tr[1]//a[contains(text(),'Details')]")
    protected WebElement asDriverDetailsButton;

    @FindBy(id = "accept0")
    protected WebElement asDriverAcceptedButton;

    @FindBy(id = "deny0")
    protected WebElement asDriverDeniedButton;

    @FindBy(xpath = "//input[@value='Confirm']")
    protected WebElement asDriverConfirmButton;

    @FindBy(xpath = "//tr[1]//a[contains(text(),'Remove')]")
    protected WebElement asDriverRemoveLink;

    @FindBy(xpath = "//div[@id='removeModalWithPassengers']//button[contains(text(),'Remove')]")
    protected WebElement asDriverRemoveWithPassengersButton;

    @FindBy(xpath = "//div[@id='removeModalWithoutPassengers']//button[contains(text(),'Remove')]")
    protected WebElement asDriverRemoveWithOutPassengersButton;

    @FindBy(xpath = "//div[@id='routeResults']//td[1]/a")
    protected WebElement fromTripLink;

    @FindBy(xpath = "//td[5]")
    private WebElement SeatsTaken;

    @FindBy(xpath = "//td[4]")
    private WebElement SeatsTotal;

    @FindBy(xpath = "//td[6]")
    private WebElement SeatsLeft;

    //Driver Calendar
    @FindBy(xpath = "//div[@id='driverCalendar']//button[@ data-calendar-nav='prev']")
    protected WebElement driverCalendarPrevButton;

    @FindBy(xpath = "//div[@id='driverCalendar']//button[@ data-calendar-nav='next']")
    protected WebElement driverCalendarNextButton;

    @FindBy(xpath = "//div[@id = 'driverCalendar']//button[@data-calendar-view = 'day']")
    protected WebElement driverCalendarDayFilter;

    @FindBy(xpath = "//div[@id = 'driverCalendar']//button[@data-calendar-view = 'week']")
    protected WebElement driverCalendarWeekFilter;

    @FindBy(xpath = "//div[@id = 'driverCalendar']//button[@data-calendar-view = 'month']")
    protected WebElement driverCalendarMonthFilter;

    @FindBy(xpath = "//div[@id = 'driverCalendar']//button[@data-calendar-view = 'year']")
    protected WebElement driverCalendarYearFilter;


    //Passenger Calendar
    @FindBy(xpath = "//div[@id='passengerCalendar']//button[@ data-calendar-nav='prev']")
    protected WebElement passengerCalendarPrevButton;

    @FindBy(xpath = "//div[@id='passengerCalendar']//button[@ data-calendar-nav='next']")
    protected WebElement passengerCalendarNextButton;

    @FindBy(xpath = "//div[@id = 'passengerCalendar']//button[@data-calendar-view = 'day']")
    protected WebElement passengerCalendarDayFilter;

    @FindBy(xpath = "//div[@id = 'passengerCalendar']//button[@data-calendar-view = 'week']")
    protected WebElement passengerCalendarWeekFilter;

    @FindBy(xpath = "//div[@id = 'passengerCalendar']//button[@data-calendar-view = 'month']")
    protected WebElement passengerCalendarMonthFilter;

    @FindBy(xpath = "//div[@id = 'passengerCalendar']//button[@data-calendar-view = 'year']")
    protected WebElement passengerCalendarYearFilter;

    @FindBy(xpath = "//div[@class = 'pull-left day-event day-highlight dh-event-important']")
    protected WebElement passengerCalendarDayMessage;

    @FindBy(xpath = "//div[@data-event-class='event-important']")
    protected WebElement passengerCalendarWeekMessage;

    @FindBy(xpath = "//div[@class='events-list']")
    protected WebElement passengerCalendarMonthMessage;

    @FindBy(xpath = "//a[@class='event-item']")
    protected WebElement passengerCalendarMonthItem;

    @FindBy(xpath = "//div[@id ='calendarPassender']/div/div[2]/div[3]")
    protected WebElement passengerCalendarYearMessage;

    @FindBy(xpath = "//a[@class='event-item']")
    protected WebElement passengerCalendarYearItem;

    //Methods
    public MainPageAfterLogin gotoMainPage() {
        mainPageLink.click();
        return new MainPageAfterLogin();
    }

    public void gotoMyTripsMainPage() {
        myTripsLink.click();
    }

    public void gotoAsDriverTab() {
        asDriverTab.click();
    }

    public void gotoAsPassengerTab() {
        asPassengerTab.click();
    }

    public void gotoDriverCalendar() {
        driverCalendarTab.click();
    }

    public void gotoPassengerCalendar() {
        waitForElementFindBy(passengerCalendarTab);
        passengerCalendarTab.click();
    }

    public AddTripPage gotoAddTripPage() {
        asDriverTab.click();
        asDriverAddTripButton.click();
        return new AddTripPage();
    }

    public void acceptPassengerTrip(String idtr) {
        asDriverTab.click();
        Driver.getInstance().findElement(By.xpath("//a[@href='javascript:getPassengersCountForJoining(" + idtr + ")']")).click();
        asDriverAcceptedButton.click();
        asDriverConfirmButton.click();
    }

    public void rejectPassengerTrip(String idtr) {
        asDriverTab.click();
        Driver.getInstance().findElement(By.xpath("//a[@href='javascript:getPassengersCountForJoining(" + idtr + ")']")).click();
        asDriverDeniedButton.click();
        asDriverConfirmButton.click();
    }

    public void removePassengerTrip() {
        asDriverTab.click();
        asDriverRemoveLink.click();
        try {
            asDriverRemoveWithPassengersButton.click();
        } catch (Exception e1) {
        }
        try {
            asDriverRemoveWithOutPassengersButton.click();
        } catch (Exception e2) {
        }
    }

    public boolean amountOfSeatsLeft(String ammOfSeats) {
        int totalLeft = 0;
        int actualAmountOfSeatsTaken = Integer.valueOf(ammOfSeats);
        int seatsTotal = Integer.valueOf(SeatsTotal.getText());
        totalLeft = seatsTotal - actualAmountOfSeatsTaken;
        if (totalLeft == Integer.valueOf(SeatsLeft.getText())) {
            return true;
        } else {
            return false;
        }
    }

    public int curentAmountOfSeatsLeft() {
        return Integer.valueOf(SeatsTaken.getText());
    }

    public void statusIsSubmitted(String verification) {
        assertEquals(verification, getStatus());
    }

    public String getStatus() {
        return asPassengerStatusSubmitted.getText().toLowerCase();
    }

    public String getTripId() {
        String idtr = "";
        idtr = fromTripLink.getAttribute("href");
        idtr = idtr.substring(21, 25);
        return idtr;
    }

    public void verifyTripStatusAsPassengerByDayFilter(String verifyWord) {
        getCurrentScreenshots("D:\\Viktoriia_Ishchuk\\gitProjects\\podorozhniki_us11\\screenshots\\", "verifyDay");
        assertTrue(passengerCalendarDayMessage.getText().matches(".*" + verifyWord + ".*"));
    }

    public void verifyTripStatusAsPassengerByWeekFilter(String verifyWord) {
        passengerCalendarWeekFilter.click();
        getCurrentScreenshots("D:\\Viktoriia_Ishchuk\\\\gitProjects\\\\podorozhniki_us11\\\\screenshots\\", "verifyWeek");
        assertTrue(passengerCalendarWeekMessage.getText().matches(".*" + verifyWord + ".*"));
    }

    public void verifyTripStatusAsPassengerByMonthFilter(String verifyWord) {
        passengerCalendarMonthFilter.click();
        passengerCalendarMonthMessage.click();
        assertTrue(passengerCalendarMonthItem.getText().matches(".*" + verifyWord + ".*"));
        getCurrentScreenshots("D:\\Viktoriia_Ishchuk\\gitProjects\\podorozhniki_us11\\screenshots\\", "verifyMonth");
    }

    public void verifyTripStatusAsPassengerByYearFilter(String verifyWord) {
        passengerCalendarYearFilter.click();
        passengerCalendarYearMessage.click();
        assertTrue(passengerCalendarYearItem.getText().matches(".*" + verifyWord + ".*"));
        getCurrentScreenshots("D:\\Viktoriia_Ishchuk\\gitProjects\\podorozhniki_us11\\screenshots\\", "verifyYear");
    }
}
