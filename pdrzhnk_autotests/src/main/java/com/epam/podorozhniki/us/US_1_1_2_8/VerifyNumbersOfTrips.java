package com.epam.podorozhniki.us.US_1_1_2_8;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MainPageAfterLogin;

public class VerifyNumbersOfTrips {

	public String from_address = "Киев, ул. Комарова, 12";
	public String to_address = "Киев, ул. Верхний Вал, 57";
	public String driver_username = "creditnew";
	public String driver_password = "1234567";
	public String idtr = "";

	protected int numFromPageBeforeDeleting;
	protected int numFromPageAfterDeleting;
	public VerifyNumbersOfTrips() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private static Logger log = Logger.getLogger(MainPageAfterLogin.class);
	

	public void checkingNumber() {
		

		try {
			assertEquals(numFromPageAfterDeleting,
					(numFromPageAfterDeleting - 1));
		} catch (AssertionError e1) {
			log.info("numFromPageAfterDeleting is not equal numFromPageAfterDeleting");
			e1.printStackTrace();
		}

	}
	
	public MainPageAfterLogin goToMainPage(){
		return new MainPageAfterLogin(); 
	}
}
