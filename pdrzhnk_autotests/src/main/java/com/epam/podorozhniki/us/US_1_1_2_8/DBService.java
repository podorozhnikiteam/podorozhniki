package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.db.DBConnection;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MyTripsPage;

public class DBService {

	protected int numFromBaseAsDriver;
	protected int numFromBaseAsPass;
	protected int numDriverPage; 
	protected int numPassBase;

	public String from_address = "Киев, ул. Комарова, 12";
	public String to_address = "Киев, ул. Верхний Вал, 57";
	public String driver_username = "creditnew";
	public String driver_password = "1234567";
	public String passenger_username = "creditnew2";
	public String passenger_password = "1234567";
	public String idtr = "";

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
			numDriverPage = rs.getInt(1);
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

	// postconditions
	public void deletingTripsAsDriver(String query) throws SQLException {
		dbConnection = new DBConnection();
		ResultSet rs = dbConnection.queryExecutor(query);
		while (rs.next()) {
			System.out.println(rs);
		}
		log.info("Driver's trips were deleted from DB");
	}

	public void deletingTripsAsPassenger(String query)
			throws SQLException {
		dbConnection = new DBConnection();
		ResultSet rs = dbConnection.queryExecutor(query);
		while (rs.next()) {
			System.out.println(rs.getStatement()); 
		}
		log.info("Passenger's trips were deleted from DB");
	}

	public VerifyNumbersOfTrips goToVerifyNumbersOfTrips() {
		return new VerifyNumbersOfTrips();
	}
}
