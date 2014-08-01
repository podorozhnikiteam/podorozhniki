package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.db.DBConnection;
import com.epam.podorozhniki.ui.MainPageAfterLogin;

public class PostconditionsPage {

	final static Logger log = Logger.getLogger(MainPageAfterLogin.class);
	protected static String query;
	private DBConnection dbConnection;

	public MainPageAfterLogin DeletingTripsFromDB() throws SQLException {
		Driver.init();
		query = System.getProperty("US1128.queryDeletingAllTrips");

		dbConnection = PageFactory.initElements(Driver.getInstance(),
				DBConnection.class);
		ResultSet rs = dbConnection.queryExecutor(query);
		while (rs.next()) {

		}
		log.info("all driver's trips were removed from DB");
		return new MainPageAfterLogin();
	}

}
