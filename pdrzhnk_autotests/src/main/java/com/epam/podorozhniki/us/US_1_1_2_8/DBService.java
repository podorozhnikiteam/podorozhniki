package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.db.DBConnection;
import com.epam.podorozhniki.ui.MainPageAfterLogin;

public class DBService {
	
	public int numDriverBase; 
	public int numPassBase;

	private DBConnection dbConnection;

	private static Logger log = Logger.getLogger(MainPageAfterLogin.class);

	public DBService() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	public void countingTripsAsDriver(String query)
			throws SQLException {
		dbConnection = new DBConnection();
		ResultSet rs = dbConnection.queryExecutor(query);
		while (rs.next()) {
			numDriverBase = rs.getInt(1);
		}
	}

	public void countingTripsAsPassenger(String query)
			throws SQLException {
		dbConnection = new DBConnection();
		ResultSet rs = dbConnection.queryExecutor(query);
		while (rs.next()) {
			numPassBase = rs.getInt(1);
		}
	}

	public void deletingTripsAsDriver(String query) throws SQLException {
		dbConnection = new DBConnection();
		ResultSet rs = dbConnection.queryExecutor(query);
	}

	public void deletingTripsAsPassenger(String query)
			throws SQLException {
		dbConnection = new DBConnection();
		ResultSet rs = dbConnection.queryExecutor(query);
	}

}
