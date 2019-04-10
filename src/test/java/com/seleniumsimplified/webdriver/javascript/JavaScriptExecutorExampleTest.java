package com.seleniumsimplified.webdriver.javascript;


import com.seleniumsimplified.webdriver.manager.Browsers;
import com.seleniumsimplified.webdriver.manager.Driver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaScriptExecutorExampleTest {

    static WebDriver driver;

    @BeforeAll
    public static void startDriver(){
        driver = Driver.getDriver(Browsers.GOOGLECHROME);
    }
    @BeforeEach
    public void setUp(){

        driver.get("http://www.compendiumdev.co.uk/selenium/canvas_basic.html");
    }
    @AfterAll
    public static void quitBrowser(){
        driver.quit();
    }
    @Test
    public void callAJavaScriptFunctionOnThePage(){

        JavascriptExecutor js =(JavascriptExecutor)driver;
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;

        int actionsCountList = driver.findElements(By.cssSelector("#commandlist li")).size();
        assertEquals(2,actionsCountList, "By default app should have 2 actions list");

        jsExecutor.executeScript("draw(0,100,100,50,'#FF1C0A')");
        jsExecutor.executeScript("draw(1,20,20,50,'#FF1C0A')");

        actionsCountList = driver.findElements(By.cssSelector("#commandlist li")).size();
        assertEquals(4, actionsCountList,"Calling draw should add an action");
    }
}

