package com.epam.podorozhniki.ui;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.epam.podorozhniki.core.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class MethodsPage {
	
	public int numFromPage;

	public MethodsPage waitForElementFindBy(WebElement element) {
		WebDriverWait wait = new WebDriverWait(Driver.getInstance(), 15, 1);
		wait.until(ExpectedConditions.visibilityOf(element));
		return this;
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
			assertTrue(alert.getText().matches(".*" + alertMessage + ".*"));
			alert.accept();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// count trips on the page
	public int countTripsOnThePage(By button_for_list, By nextPage) {
		List<WebElement> buttonJoins = Driver.getInstance().findElements(
				button_for_list);
		int numElem = buttonJoins.size();
		if (numElem == 0) {
			numFromPage = 0;
			// log.error("Preconditions are wrong: there is no trip");
			System.out.println("There are no trip on the page");

		} else {
			numFromPage = 0;
			try {
				List<WebElement> allPages = Driver.getInstance().findElements(
						nextPage);
				int next = allPages.size();
				outer: while (next != 0) {
					buttonJoins = Driver.getInstance().findElements(
							button_for_list);
					numFromPage = numFromPage + buttonJoins.size();
					if (Driver.getInstance().findElement(nextPage).getText()
							.contains("�")) {
						break;
					} else {
						(new WebDriverWait(Driver.getInstance(), 10)).until(
								ExpectedConditions
										.visibilityOfElementLocated(nextPage))
								.click();
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						continue outer;
					}
				}
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		}
		// log.info("there are " + numFromPage + " trips on the main page");
		System.out.println("there are " + numFromPage
				+ " trips on the main page");
		return numFromPage;
	}

	public boolean isElementPresent(By locator) {
		Driver.getInstance().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		List<WebElement> elements = Driver.getInstance().findElements(locator);
		Driver.getInstance().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return elements.size() > 0 && elements.get(0).isDisplayed();
	}
	
    public boolean isElementPresent(WebElement webElement) {
        boolean exists = false;

        Driver.getInstance().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        try {
            webElement.getTagName();
            exists = true;
        } catch (NoSuchElementException e) {}

        Driver.getInstance().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        return exists && webElement.isDisplayed();
    }
}