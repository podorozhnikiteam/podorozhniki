package com.epam.podorozhniki.ui;

import com.epam.podorozhniki.core.Driver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriverException;
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

	@FindBy(id = "my_trips")
	protected WebElement myTripsLink;

	@FindBy(xpath = "//li[@id='li_driver']/a")
	protected WebElement asDriverTab;

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

	public MyTripsPage addTrip(String from, String to)
			throws InterruptedException, WebDriverException {
		fromField.clear();
		fromField.sendKeys(from);
		toField.clear();
		toField.sendKeys(to);
		buildOnMapBtn.click();
		createTripBtn.click();
		Thread.sleep(1000);
		assertEquals("Your trip successfully saved!", closeAlertAndGetItsText());
		backToTripsBtn.click();
		return new MyTripsPage();
	}

	@SuppressWarnings("finally")
	public MyTripsPage addTripZoja(String from, String to)
			throws InterruptedException, WebDriverException {
		waitForElementFindBy(fromField);
		fromField.clear();
		fromField.sendKeys(from);
		waitForElementFindBy(toField);
		toField.clear();
		toField.sendKeys(to);
		waitForElementFindBy(buildOnMapBtn);
		buildOnMapBtn.click();
		waitForElementFindBy(createTripBtn);
		createTripBtn.click();
		try {
			assertEquals("Your trip successfully saved!",
					closeAlertAndGetItsText());
			waitForElementFindBy(backToTripsBtn);
			backToTripsBtn.click();
		} catch (WebDriverException e) {
			e.printStackTrace();
		} finally {
			return new MyTripsPage();
		}
	}

}
