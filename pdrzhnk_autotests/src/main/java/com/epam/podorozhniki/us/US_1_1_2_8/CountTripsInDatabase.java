package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.db.DBConnection;
import com.epam.podorozhniki.ui.MainPageAfterLogin;

public class CountTripsInDatabase {

	protected int numFromBaseAsDriverBeforeDelet;
	protected int numFromBaseAsPassBeforeDelet;
	protected int numFromBaseAsDriverAfterDelet;
	protected int numFromBaseAsPassAfterDelet;

	public String from_address = "Киев, ул. Комарова, 12";
	public String to_address = "Киев, ул. Верхний Вал, 57";
	public String driver_username = "creditnew";
	public String driver_password = "1234567";
	public String passenger_username = "creditnew2";
	public String passenger_password = "1234567";
	public String idtr = "";
	protected static String query;

	private DBConnection dbConnection;

	private static Logger log = Logger.getLogger(MainPageAfterLogin.class);

	public CountTripsInDatabase() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	// count trips before deleting as driver
	public void countingTripsBeforeDeletingAsDriver() throws SQLException {
		Driver.init();
		query = System.getProperty("US1128.queryAsDriverBeforeDeleting");
		dbConnection = PageFactory.initElements(Driver.getInstance(),
				DBConnection.class);
		ResultSet rs = dbConnection.queryExecutor(query);
		while (rs.next()) {
			numFromBaseAsDriverBeforeDelet = rs.getInt(1);
		}
		log.info("driver has " + numFromBaseAsDriverBeforeDelet
				+ " trips in the DB");
	}

	// count trips before deleting as passenger
	public void countingTripsBeforeDeletingAsPassenger() throws SQLException {
		Driver.init();
		query = System.getProperty("US1128.queryAsPassengerBeforeDeleting");
		dbConnection = PageFactory.initElements(Driver.getInstance(),
				DBConnection.class);
		ResultSet rs = dbConnection.queryExecutor(query);
		while (rs.next()) {
			numFromBaseAsPassBeforeDelet = rs.getInt(1);
		}
		log.info("passenger has " + numFromBaseAsPassBeforeDelet
				+ " trips in the DB");
	}

	// count trips after deleting as driver
	public void countingTripsAfterDeletingAsDriver() throws SQLException {
		Driver.init();
		query = System.getProperty("US1128.queryAsDriverAfterDeleting");
		dbConnection = PageFactory.initElements(Driver.getInstance(),
				DBConnection.class);
		ResultSet rs = dbConnection.queryExecutor(query);
		while (rs.next()) {
			numFromBaseAsDriverAfterDelet = rs.getInt(1);
		}
		log.info("driver has " + numFromBaseAsDriverAfterDelet
				+ " trips after deleting in the DB");
	}

	// count trips after deleting as passenger
	public void countingTripsAfterDeletingAsPassenger() throws SQLException {
		Driver.init();
		query = System.getProperty("US1128.queryAsPassengerAfterDeleting");
		dbConnection = PageFactory.initElements(Driver.getInstance(),
				DBConnection.class);
		ResultSet rs = dbConnection.queryExecutor(query);
		while (rs.next()) {
			numFromBaseAsPassAfterDelet = rs.getInt(1);
		}
		log.info("passenger has " + numFromBaseAsPassAfterDelet
				+ " trips after deleting in the DB");
	}

	public TripRemoving goToTripRemovingPage() {
		return new TripRemoving();
	}

	public VerifyNumbersOfTrips goToVerifyNumbersOfTrips() {
		return new VerifyNumbersOfTrips();
	}
}
