package com.epam.podorozhniki.ui;

public enum UITitles {
    // Here is template on how WebElements should look:
    // pageName.elementName:locatorName=locatorValue

    LOGINFIELD("MainPageNotLogged.loginFieldLocator:id=inputUsername");

    private String locator;

    UITitles(String locator) {
        this.locator = locator;
    }

    public By getLocator(locator) {
    }
}
