package com.epam.podorozhniki.ui;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class MethodsPage {

	protected WebDriver wdriver;

	@FindBy(xpath = "//a[@class='btn btn-default']")
	private WebElement logout;

	// for ZoyaS  
	public MethodsPage waitForElementFindBy(WebElement element) {
		WebDriverWait wait = new WebDriverWait(wdriver, 15, 1);
		wait.until(ExpectedConditions.visibilityOf(element));
		return this;
	}

    //Get screenshots of current page
    public void getCurrentScreenshots(String filePath, String fileName){
        try {
            String screenPath = filePath;
            String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime());
            String fileID = fileName + "_" + timeStamp;
            String screenName = String.format("%s.png", fileID);
            screenPath = screenPath +screenName;
            File screenshot = ((TakesScreenshot) wdriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(screenPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Accept alert with correct alert message.
    public void checkAlert(String alertMessage) {
        try {
            WebDriverWait wait = new WebDriverWait(wdriver, 20);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = wdriver.switchTo().alert();
            assertTrue(alert.getText().matches(".*"+alertMessage+".*"));
            alert.accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
