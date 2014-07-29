package com.epam.podorozhniki.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	public MainPageBeforeLogin logoutPerform() {
		waitForElementFindBy(logout);
		logout.click();
		return new MainPageBeforeLogin();
	}

}
