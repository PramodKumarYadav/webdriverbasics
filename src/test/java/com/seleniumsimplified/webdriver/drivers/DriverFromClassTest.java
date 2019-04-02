package com.seleniumsimplified.webdriver.drivers;

import com.seleniumsimplified.webdriver.manager.Browsers;
import com.seleniumsimplified.webdriver.manager.MyDriver;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * cover nuances with the Chrome Driver
 */
public class DriverFromClassTest {

    // As of Chrome v35, it reports an error regarding --ignore-certificate-errors
    // to remove this error start the chrome driver with
    // args "test-type"

    public static WebDriver driver;
    public static MyDriver myDriver;

    @AfterEach
    public void quitDriver(){

        driver.quit();
    }

    @Test
    public void defaultDriverUsage(){
        myDriver= new MyDriver();
        driver = myDriver.getDriver();
        System.out.println(System.getenv());
        driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        assertThat(driver.getTitle(), is("HTML Form Elements"));
    }
    @Test
    public void basicEdgeDriverUsage(){
        myDriver= new MyDriver(Browsers.EDGE);
        driver = myDriver.getDriver();

        driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        assertThat(driver.getTitle(), is("HTML Form Elements"));
    }

    @Test
    public void basicChromeDriverUsage(){
        myDriver= new MyDriver(Browsers.GOOGLECHROME);
        driver = myDriver.getDriver();

        driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        assertThat(driver.getTitle(), is("HTML Form Elements"));
    }

}

