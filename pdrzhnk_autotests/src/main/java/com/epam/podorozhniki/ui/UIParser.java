package com.epam.podorozhniki.ui;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class UIParser {
    private String pageName;
    private String elementName;
    private String locatorName;
    private String locatorValue;
    private Map locators = new HashMap<String, By>();

    public void addToMap(String text) throws Exception {
        pageName = text.split("\\.")[0];
        elementName = text.split("\\.")[1].split(":")[0];
        locatorName = text.split("\\.")[1].split(":")[1].split("=")[0];
        locatorValue = text.split("\\.")[1].split(":")[1].split("=")[1];

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
                throw new Exception();
        }
    }
}
