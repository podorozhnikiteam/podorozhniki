package com.epam.podorozhniki.ui;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MethodsPage {

	protected WebDriver wdriver;

	public MethodsPage(WebDriver driver) {
		this.wdriver = driver;
	}

	@FindBy(xpath = "//a[@class='btn btn-default']")
	private WebElement logout;

	// for ZoyaS
	public MethodsPage waitForElementFindBy(WebElement element) {
		WebDriverWait wait = new WebDriverWait(wdriver, 15, 1);
		wait.until(ExpectedConditions.visibilityOf(element));
		return this;
	}

	public void waitForPageLoad() {
		wdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public MainPageBeforeLogin logoutPerform() {
		waitForElementFindBy(logout);
		logout.click();
		return new MainPageBeforeLogin(wdriver);
	}

}
