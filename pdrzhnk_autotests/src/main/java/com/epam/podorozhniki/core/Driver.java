package com.epam.podorozhniki.core;

public class Driver {
    private static Driver ourInstance = new Driver();

    public static Driver getInstance() {
        return ourInstance;
    }

    private Driver() {
    }
}
