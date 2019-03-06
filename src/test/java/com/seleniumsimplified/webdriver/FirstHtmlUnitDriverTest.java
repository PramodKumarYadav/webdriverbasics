package com.seleniumsimplified.webdriver;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstHtmlUnitDriverTest {

    @Test
    public void testAppAssertTrue() {
        assertTrue(true);
    }
    @Test
    public void driverIsKingWithWebdriver() {

        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://www.compendiumdev.co.uk/selenium");

        assertTrue(driver.getTitle().startsWith("Selenium Simplified"));

    }
    @Test
    public void driverIsKingWithHtmlDriver() {

        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.get("http://www.compendiumdev.co.uk/selenium");

        assertTrue(driver.getTitle().startsWith("Selenium Simplified"));

    }
    @Test
    public void driverIsKingExploreDriverOptions() {

//        HtmlUnitDriver driver = new HtmlUnitDriver();

        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://www.compendiumdev.co.uk/selenium");

        System.out.println(((HtmlUnitDriver) driver).getBrowserVersion());
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getPageSource());
        System.out.println(((HtmlUnitDriver) driver).getCapabilities());
        System.out.println(((HtmlUnitDriver) driver).getKeyboard());
        System.out.println(((HtmlUnitDriver) driver).getMouse());
        System.out.println(driver.getWindowHandle());


        assertTrue(driver.getTitle().startsWith("Selenium Simplified"));

    }
}
