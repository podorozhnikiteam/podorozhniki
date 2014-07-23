package com.epam.podorozhniki.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    private static WebDriver driver;

    public static WebDriver getInstance() {
        return driver;
    }

    private Driver() {}

    public static void setDriver(WebDriver driverInput) {
        driver = driverInput;
    }

    public static void init() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static void tearDown() {
        getInstance().quit();
    }
}
