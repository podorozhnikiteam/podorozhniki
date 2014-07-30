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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.podorozhniki.core.Driver;

import static org.junit.Assert.assertTrue;

public class MethodsPage {

	protected WebDriver wdriver = Driver.getInstance();
	public int numFromPage;

	@FindBy(xpath = "//a[@class='btn btn-default']")
	private WebElement logout;

	public MethodsPage waitForElementFindBy(WebElement element) {
		WebDriverWait wait = new WebDriverWait(wdriver, 15, 1);
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
			File screenshot = ((TakesScreenshot) wdriver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Accept alert with correct alert message.
	public void checkAlert(String alertMessage) {
		try {
			WebDriverWait wait = new WebDriverWait(wdriver, 20);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = wdriver.switchTo().alert();
			assertTrue(alert.getText().matches(".*" + alertMessage + ".*"));
			alert.accept();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// count trips on the page
	public int countTripsOnThePage(By button_for_list, By nextPage) {
		List<WebElement> buttonJoins = wdriver.findElements(button_for_list);
		int numElem = buttonJoins.size();
		if (numElem == 0) {
			numFromPage = 0;
			// log.error("Preconditions are wrong: there is no trip");
			System.out.println("There are no trip on the page");

		} else {
			numFromPage = 0;
			try {
				List<WebElement> allPages = wdriver.findElements(nextPage);
				int next = allPages.size();
				outer: while (next != 0) {
					buttonJoins = wdriver.findElements(button_for_list);
					numFromPage = numFromPage + buttonJoins.size();
					if (wdriver.findElement(nextPage).getText().contains("»")) {
						break;
					} else {
						(new WebDriverWait(wdriver, 10)).until(
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
}