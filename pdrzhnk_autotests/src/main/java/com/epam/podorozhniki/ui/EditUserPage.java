package com.epam.podorozhniki.ui;

import com.epam.podorozhniki.core.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditUserPage {

    public EditUserPage() {
        PageFactory.initElements(Driver.getInstance(), this);
    }

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "surname")
    private WebElement surnameField;

    @FindBy(id = "dateOfBirthpicker")
    private WebElement dateOfBirthPicker;

    @FindBy(id = "gender")
    private WebElement genderButton;

    @FindBy(id = "phone")
    private WebElement phoneField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "skype")
    private WebElement skypeField;

    @FindBy(xpath = ".//*[@id='edit_profile']/fieldset/div[8]/a")
    private WebElement addSocialNetworkButton;

    @FindBy(id = "project")
    private WebElement currentProjectField;

    @FindBy(id = "additionalInfo")
    private WebElement additionalInforField;

    @FindBy(xpath = ".//*[@id='edit_profile']/fieldset/div[13]/div/button[1]")
    private WebElement confirmChangesButton;

    @FindBy(xpath = ".//*[@id='edit_profile']/fieldset/div[13]/div/button[2]")
    private WebElement cancelButton;
}
