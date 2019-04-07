package com.seleniumsimplified.webdriver.windows;

import com.seleniumsimplified.webdriver.manager.Browsers;
import com.seleniumsimplified.webdriver.manager.Driver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WindowsManageExampleTest {
    private WebDriver driver;

    @BeforeEach
    public void quitToRestart(){

        driver = Driver.getDriver(Browsers.GOOGLECHROME);
    }
    @BeforeEach
    public void setUp() {

        driver.get("http://www.compendiumdev.co.uk/selenium/frames");
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getWindowHandle());
    }
    @AfterEach
    public void quitToClean(){
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void manageWindow(){

        final int widthToSet;

        // moved to 40 for Y to cope with Mac drop down menu
        Point originalPosition = driver.manage().window().getPosition();
        System.out.println(originalPosition.x);
        System.out.println(originalPosition.getY());

        driver.manage().window().setPosition(new Point(10,40));
        Point positionWindow = driver.manage().window().getPosition();

        assertEquals(10, positionWindow.getX());
        assertEquals(40, positionWindow.getY());

        // when originally written screens and browsers were smaller
        Dimension originalSize = driver.manage().window().getSize();
        System.out.println(originalSize.height);
        System.out.println(originalSize.getWidth());

        Dimension originalWinSizes = driver.manage().window().getSize();
        int originalWidth = originalWinSizes.getWidth();

        widthToSet = 500;
        driver.manage().window().setSize(new Dimension(widthToSet,400));
        Dimension winSizes = driver.manage().window().getSize();

        assertTrue(originalWidth != winSizes.getWidth(),"Expected window width to change");

        // ideally window should be `widthToSet` but I'm less concerned about the exact size and more that it isn't the default
        // TODO make widthToSet a value that works on modern browsers and resolutions
        assertEquals(400, winSizes.getHeight());
        assertEquals(widthToSet, winSizes.getWidth());

    }
}
