package com.epam.podorozhniki.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MethodsPage;
import com.epam.podorozhniki.ui.MyRoutesPage;

/**
 * Created by Zoja_S on 7/28/2014.
 */

public class TripRemoving extends MethodsPage {

	public TripRemoving() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	
}
