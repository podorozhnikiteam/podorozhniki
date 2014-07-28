package com.epam.podorozhniki.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MethodsPage;
import com.epam.podorozhniki.ui.MyTrips;

/**
 * Created by Zoja_S on 7/28/2014.
 */

public class TripRemoving extends MethodsPage {

	public TripRemoving() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private static Logger log = Logger.getLogger(MainPageAfterLogin.class);
	private String From_point;
	private String To_point;

	// @FindBy(xpath =
	// ".//a[text()='Киевская область, Киев, Дарницкий район, проспект Николая Бажана, 19']")
	@FindBy(xpath = ".//div[@id='routeResults']//tr[2]/td[1]")
	private WebElement from_point;

	// @FindBy(xpath =
	// ".//a[text()='Киевская область, Киев, Соломенский район, улица Кудряшова, 20']")
	@FindBy(xpath = ".//div[@id='routeResults']//tr[2]/td[2]")
	private WebElement to_point;

	@FindBy(xpath = ".//div[@id='routeResults']//tr[2]/td[9]")
	private WebElement remove_trip;

	public MyTrips GetTripPoints() {

		try {
			waitForElementFindBy(from_point);
			From_point = from_point.getText();
			log.info("From_point: " + From_point);

		} catch (NoSuchElementException e) {
			log.error("error in point 'from_point'"); // ?????
			e.printStackTrace();
		}
		try {
			waitForElementFindBy(to_point);
			To_point = to_point.getText();
			log.info("To_point: " + To_point);

		} catch (NoSuchElementException e) {
			log.error("error in point 'to_point'"); // ????
			e.printStackTrace();
		}

		try {
			waitForElementFindBy(remove_trip);
			remove_trip.click();
		} catch (NoSuchElementException e) {
			log.error("'remove_trip' was not found"); // ????
			e.printStackTrace();
		}

		
		
		
		
		
		return new MyTrips();
	}
}
