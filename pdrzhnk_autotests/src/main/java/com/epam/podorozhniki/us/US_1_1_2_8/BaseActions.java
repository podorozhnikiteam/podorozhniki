package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.db.DBConnection;
import com.epam.podorozhniki.ui.MethodsPage;

/*
 * Created by Zoja_Sharova
 */

public class BaseActions extends MethodsPage {

	public static ReadingDatafile rd;
	private static DBConnection dbConnect;

	private static Logger log = Logger.getLogger(BaseActions.class);

	static ResultSet rs_driver;
	static ResultSet rs_pass;

	@Before
	public void setUp() {
		Driver.init();
		rd = new ReadingDatafile();
		rd.readingDataFile();
		Driver.getInstance().manage().window().maximize();
		Driver.getInstance().get(rd.baseUrl);
	}

	@After
	public void afterTest() throws SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		dbConnect = new DBConnection();
		rs_pass = dbConnect.queryExecutor(rd.queryDeletingAllPassengerTrips);
		rs_driver = dbConnect.queryExecutor(rd.queryDeletingAllDriverTrips);
		log.info("AfterClass: Trips were deleted");
		Driver.tearDown();
	}
}
