package com.epam.podorozhniki.us.US_001;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.db.DBConnection;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MethodsPage;

public class MainPageService extends MethodsPage {

	protected int numFromPage;
	protected int numFromBase;

	private DBConnection dbConnection;

	private static Logger log = Logger.getLogger(MainPageService.class);

	public MainPageService() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	public MainPageAfterLogin countTripsInDatabase(String query)
			throws SQLException {
		dbConnection = new DBConnection();
		ResultSet rs = dbConnection.queryExecutor(query);
		while (rs.next()) {
			numFromBase = rs.getInt(1);
		}
		log.info("there are " + numFromBase + " trips in the DB");
		return new MainPageAfterLogin();
	}

}
