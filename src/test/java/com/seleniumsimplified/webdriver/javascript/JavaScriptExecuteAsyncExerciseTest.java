package com.seleniumsimplified.webdriver.javascript;


import com.seleniumsimplified.webdriver.manager.Browsers;
import com.seleniumsimplified.webdriver.manager.Driver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class JavaScriptExecuteAsyncExerciseTest {

    private static WebDriver driver;


    @BeforeAll
    public static void startDriver(){
        driver = Driver.getDriver(Browsers.GOOGLECHROME);
    }
    @BeforeEach
    public void resetBeforeTest() {
        driver.get("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");
        driver.navigate().refresh();
    }
    @AfterAll
    public static void quitBrowser() {
        driver.quit();
    }
    @Test
    public void waitInBrowserForTimeSample(){

        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        long start = System.currentTimeMillis();
        ((JavascriptExecutor) driver).executeAsyncScript(
                "window.setTimeout(arguments[arguments.length - 1], 500);");

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
        assertTrue((System.currentTimeMillis() - start) > 500,"Elapsed time should be greater than 500 milli");
    }

    @Test
    public void useXMLHttpRequest(){


        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        Object response = ((JavascriptExecutor) driver).executeAsyncScript(
                "var callback = arguments[arguments.length - 1];" +
                        "var xhr = new XMLHttpRequest();" +
                        "xhr.open('GET', '/selenium/ajaxselect.php?id=2', true);" +
                        "xhr.onreadystatechange = function() {" +
                        "  if (xhr.readyState == 4) {" +
                        "    callback(xhr.responseText);" +
                        "  }" +
                        "};" +
                        "xhr.send();");

        System.out.println((String)response);
        assertThat((String) response,containsString("{optionValue:10, optionDisplay: 'C++'}"));
    }
}



