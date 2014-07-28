package com.epam.podorozhniki.ui;

import com.epam.podorozhniki.core.Driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static junit.framework.TestCase.assertEquals;

/*
 * Created by Vitalii_Kalchuk on 28-Jul-14.
 */

public class AddTripPage extends MethodsPage {

    public AddTripPage() {
        PageFactory.initElements(Driver.getInstance(), this);
    }

    private boolean acceptNextAlert = true;

    @FindBy(xpath = "test")
    public static WebElement xxx;

    @FindBy(xpath = "//*[@id='starttimepicker']")
    public static WebElement timeField;

    @FindBy(xpath = "//*[@id='geoStart']")
    private WebElement fromField;

    @FindBy(xpath = "//*[@id='geoFinish']")
    private WebElement toField;

    @FindBy(xpath = "//*[@id='findroute']/div/form/fieldset/div[4]/div/input[1]")
    private WebElement buildOnMapBtn;

    @FindBy(xpath = "//*[@id='findroute']/div/form/fieldset/div[10]/div/input")
    private WebElement createTripBtn;

    @FindBy(xpath = "//*[@id='findroute']/div/form/fieldset/div[10]/div/a")
    private WebElement backToTripsBtn;

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = Driver.getInstance().switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

    public void addTrip(String from, String to) throws InterruptedException {
        fromField.clear();
        fromField.sendKeys(from);
        toField.clear();
        toField.sendKeys(to);
        buildOnMapBtn.click();
        createTripBtn.click();
        assertEquals("Your trip successfully saved!", closeAlertAndGetItsText());
        backToTripsBtn.click();
    }
}