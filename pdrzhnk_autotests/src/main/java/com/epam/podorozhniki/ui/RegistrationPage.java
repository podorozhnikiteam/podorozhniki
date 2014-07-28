package com.epam.podorozhniki.ui;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;

public class RegistrationPage {
	
	@FindBy(id = "login")
	WebElement login;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "confirmPassword")
	WebElement confirmPassword;
	
	@FindBy(id = "phone")
	WebElement phone;
	
	@FindBy(name = "agree")
	WebElement checkbox;

	@FindBy (id = "b")
	WebElement submitButton;
	
	public RegistrationPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	public void typeLogin() {
		this.login.clear();
		this.login.sendKeys(System.getProperty("test.loginForRegistrationNewUser"));
	}


	public void typePassword() {
		this.password.clear();
		this.password.sendKeys(System.getProperty("test.passwordForRegistrationNewUser"));
	}
	
	public void confirmPassword() {
		this.confirmPassword.clear();
		this.confirmPassword.sendKeys(System.getProperty("test.passwordForRegistrationNewUser"));
	}
	
	public void typephone() {
		this.phone.clear();
		this.phone.sendKeys(System.getProperty("test.phoneForRegistrationNewUser"));
	}
	
	public void agreeChekButton() {
		this.checkbox.click();
		this.checkbox.isSelected();
	}
	
	public MainPageAfterLogin typeSubmitButton() {
		this.submitButton.click();
		return new MainPageAfterLogin();
	}
}
