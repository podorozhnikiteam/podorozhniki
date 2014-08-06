package com.epam.podorozhniki.ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.junit.Assert.assertTrue;
import com.epam.podorozhniki.core.Driver;

/**
 * Created by Zoja_Sharova on 7/25/2014
 */

public class MainPageAfterLogin extends MethodsPage {

	private static Logger log = Logger.getLogger(MainPageAfterLogin.class);
	private MethodsPage methodsPage;

	public MainPageAfterLogin() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	@FindBy(xpath = "//div[1]/div[1]/div[2]/div/div[1]/span")
	protected WebElement username;

	@FindBy(className = "errorblock")
	protected WebElement error;

	@FindBy(id = "my_trips")
	protected WebElement myTripsLink;

	@FindBy(id = "geoStart")
	protected WebElement fromAddressField;

	@FindBy(id = "geoFinish")
	protected WebElement toAddressField;

	@FindBy(id = "anydatecheck")
	protected WebElement anyDateCheckPoint;

	@FindBy(xpath = "//input[@value='Find route']")
	protected WebElement findTripButton;

	@FindBy(id = "save_btn")
	protected WebElement joinSeatsOkButton;

	@FindBy(xpath = "//tr[1]//button[contains(text(),'Join')]")
	protected WebElement joinTripButton;

    @FindBy(id = "joinSeats")
    private WebElement AmountOfSeatsToTake;

	private By join_button = By.xpath("//button[contains(text(),'Join')]");
	private By nextPage = By.xpath("//li[@class='active']/following-sibling::*[1]/self::li/a");

	public MyTripsPage goToMyTripsPage() {
		myTripsLink.click();
		return new MyTripsPage();
	}

    public void joinTripByPassenger(String fromAddress, String toAddress, String idtr, String amOfSeats){
        fromAddressField.clear();
        fromAddressField.sendKeys(fromAddress);
        toAddressField.clear();
        toAddressField.sendKeys(toAddress);
        anyDateCheckPoint.click();
        findTripButton.click();
        Driver.getInstance().findElement(By.xpath("//button[@idtr='" + idtr + "']")).click();
        AmountOfSeatsToTake.clear();
        AmountOfSeatsToTake.sendKeys(amOfSeats);
        joinSeatsOkButton.click();
        checkAlert("Successfully");
    }

	// count trips on the main page
	public int countTripsOnTheMainPage(By button_for_list, By next_page_button) {
		methodsPage = PageFactory.initElements(Driver.getInstance(), MethodsPage.class);
		countTripsOnThePage(join_button, nextPage);
		return numFromPage; 
	}

    public void verifyLogoutButton() { assertTrue(isElementPresent(logoutButton));}

    public void verifyNameDisplayed(String loginName) {username.getText().equals(loginName); }

    public void verifyLoginError(String incorrectLoginErrorMessage) {error.getText().equals(incorrectLoginErrorMessage);}
}
