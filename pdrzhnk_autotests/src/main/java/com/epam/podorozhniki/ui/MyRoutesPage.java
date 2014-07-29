package com.epam.podorozhniki.ui;

import com.epam.podorozhniki.core.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Viktoriia_Ishchuk on 7/25/2014.
 */
public class MyRoutesPage {
    public MyRoutesPage(){
        PageFactory.initElements(Driver.getInstance(), this);
    }

    //Tabs
    @FindBy(id = "li_passenger")
    private WebElement asPassengerTab;

    @FindBy(id = "li_driver")
    private WebElement asDriverTab;

    @FindBy(id = "li_driverCalendar")
    private WebElement driverCalendarTab;

    @FindBy(id = "li_passengerCalendar")
    private WebElement passengerCalendarTab;

    //As Passenger Tab

    //As Driver Tab
    @FindBy(xpath ="//div[@class ='control-group span8']/a")
    private WebElement asDriverAddTripButton;

    @FindBy(xpath ="//tr[1]//a[contains(text(),'Details')]")
    private WebElement asDriverDetailsButton;

    @FindBy(id = "accept0")
    private WebElement asDriverAcceptedButton;

    @FindBy(id = "deny0")
    private WebElement asDriverDeniedButton;

    @FindBy(xpath ="//input[@value='Confirm']")
    private WebElement asDriverConfirmButton;


    //Driver Calendar
    @FindBy(xpath ="//div[@id='driverCalendar']//button[@ data-calendar-nav='prev']")
    private WebElement driverCalendarPrevButton;

    @FindBy(xpath ="//div[@id='driverCalendar']//button[@ data-calendar-nav='next']")
    private WebElement driverCalendarNextButton;

    @FindBy(xpath ="//div[@id = 'driverCalendar']//button[@data-calendar-view = 'day']")
    private WebElement driverCalendarDayFilter;

    @FindBy(xpath ="//div[@id = 'driverCalendar']//button[@data-calendar-view = 'week']")
    private WebElement driverCalendarWeekFilter;

    @FindBy(xpath ="//div[@id = 'driverCalendar']//button[@data-calendar-view = 'month']")
    private WebElement driverCalendarMonthFilter;

    @FindBy(xpath ="//div[@id = 'driverCalendar']//button[@data-calendar-view = 'year']")
    private WebElement driverCalendarYearFilter;


    //Passenger Calendar
    @FindBy(xpath ="//div[@id='passengerCalendar']//button[@ data-calendar-nav='prev']")
    private WebElement passengerCalendarPrevButton;

    @FindBy(xpath ="//div[@id='passengerCalendar']//button[@ data-calendar-nav='next']")
    private WebElement passengerCalendarNextButton;

    @FindBy(xpath ="//div[@id = 'passengerCalendar']//button[@data-calendar-view = 'day']")
    private WebElement passengerCalendarDayFilter;

    @FindBy(xpath ="//div[@id = 'passengerCalendar']//button[@data-calendar-view = 'week']")
    private WebElement passengerCalendarWeekFilter;

    @FindBy(xpath ="//div[@id = 'passengerCalendar']//button[@data-calendar-view = 'month']")
    private WebElement passengerCalendarMonthFilter;

    @FindBy(xpath ="//div[@id = 'passengerCalendar']//button[@data-calendar-view = 'year']")
    private WebElement passengerCalendarYearFilter;

    //Methods
    public void gotoAsDriverTab(){
        asDriverTab.click();
    }

    public void gotoAsPassengerTab(){
        asPassengerTab.click();
    }

    public void gotoDriverCalendar(){
        driverCalendarTab.click();
    }

    public void gotoPassengerCalendar(){
        passengerCalendarTab.click();
    }

    public AddTripPage gotoAddTripPage(){
        asDriverTab.click();
        asDriverAddTripButton.click();
        return new AddTripPage();
    }

    public void acceptPassengerTrip(){
        asDriverTab.click();
        asDriverDetailsButton.click();
        asDriverAcceptedButton.click();
        asDriverConfirmButton.click();
    }

    public void rejectPassengerTrip(){
        asDriverTab.click();
        asDriverDetailsButton.click();
        asDriverDeniedButton.click();
        asDriverConfirmButton.click();
    }
}
