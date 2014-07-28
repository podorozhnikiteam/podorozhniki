package com.epam.podorozhniki.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static WebDriver driver;

    public static WebDriver getInstance() {
        return driver;
    }

    private Driver() {}

    public static void set(WebDriver driverInput) {
        driver = driverInput;
    }

    public static void init() {
        Properties properties = new Properties();
        FileInputStream propFile;
        try {
            propFile = new FileInputStream("test.properties");
            properties.load(propFile);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        @SuppressWarnings("unchecked")
        Enumeration<String> e = (Enumeration<String>) properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            System.setProperty(key, properties.getProperty(key));
            Reporter.log(key + " - " + properties.getProperty(key), 2, true);
        }
        WebDriver driverInput = new FirefoxDriver();
        driverInput.manage().timeouts().implicitlyWait(
                Integer.parseInt(System.getProperty("test.timeout")),
                TimeUnit.SECONDS
        );
        Driver.set(driverInput);
    }

    public static void tearDown() {
        getInstance().quit();
    }
}
 