package com.epam.podorozhniki.ui;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.podorozhniki.core.Driver;

import static org.junit.Assert.assertTrue;

public class MethodsPage {

	public static int numFromPage;

	@FindBy(xpath = "//ul[@class = 'button-list']/li[2]/a")
	protected WebElement logoutButton;

	public MethodsPage waitForElementFindBy(WebElement element) {
		WebDriverWait wait = new WebDriverWait(Driver.getInstance(), 15, 1);
		wait.until(ExpectedConditions.visibilityOf(element));
		return this;
	}

	public boolean isElementPresent(By locator) {
		Driver.getInstance().manage().timeouts()
				.implicitlyWait(0, TimeUnit.SECONDS);
		List<WebElement> elements = Driver.getInstance().findElements(locator);
		Driver.getInstance().manage().timeouts()
				.implicitlyWait(30, TimeUnit.SECONDS);
		return elements.size() > 0 && elements.get(0).isDisplayed();
	}

	// Get screenshots of current page
	public void getCurrentScreenshots(String filePath, String fileName) {
		try {
			String screenPath = filePath;
			String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")
					.format(Calendar.getInstance().getTime());
			String fileID = fileName + "_" + timeStamp;
			String screenName = String.format("%s.png", fileID);
			screenPath = screenPath + screenName;
			File screenshot = ((TakesScreenshot) Driver.getInstance())
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Accept alert with correct alert message.
	public void checkAlert(String alertMessage) {
		try {
			WebDriverWait wait = new WebDriverWait(Driver.getInstance(), 20);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = Driver.getInstance().switchTo().alert();
			Thread.sleep(2000);
			assertTrue(alert.getText().matches(".*" + alertMessage + ".*"));
			alert.accept();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void catchAlert() {
		Alert alert = null;
		Wait<WebDriver> wait = new WebDriverWait(Driver.getInstance(), 5);
		try {
			alert = wait.until(ExpectedConditions.alertIsPresent());
		} catch (TimeoutException ignored) {
		}
		if (alert != null) {
			alert.accept();
		}
	}

	public int countTripsOnPage() {
		MethodsPage method = new MethodsPage();

		int n = 1;
		int count = 0;

		while (method.isElementPresent(By
				.xpath(".//*[@id='routeResults']/div/table/tbody/tr[" + n
						+ "]/td[1]"))) {
			count++;
			n++;
		}
		return count;
	}

	public int countTripsOnPassTab() {
		MethodsPage method = new MethodsPage();

		int n = 1;
		int count = 0;

		while (method.isElementPresent(By
				.xpath(".//*[@id='PassengerTrips']/div/table/tbody/tr[" + n
						+ "]/td[1]"))) {
			count++;
			n++;
		}
		return count;
	}

	public void logout() {
		logoutButton.click();
	}
}
