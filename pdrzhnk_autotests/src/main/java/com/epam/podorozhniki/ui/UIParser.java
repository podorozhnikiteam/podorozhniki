package com.epam.podorozhniki.ui;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class UIParser {
    private String pageName;
    private String elementName;
    private String locatorName;
    private String locatorValue;

    public Map getLocators() {
        return locators;
    }

    private Map locators = new HashMap<String, By>();

    public void addToMap(String text) throws IllegalArgumentException {
        String[] splitText = text.split("\\.", 4);
        pageName = splitText[0];
        elementName = splitText[1];
        locatorName = splitText[2];
        locatorValue = splitText[3];

        switch (locatorName) {
            case "id":
                locators.put(locatorName, By.id(locatorValue));
                break;
            case "xpath":
                locators.put(locatorName, By.xpath(locatorValue));
                break;
            case "className":
                locators.put(locatorName, By.className(locatorValue));
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
