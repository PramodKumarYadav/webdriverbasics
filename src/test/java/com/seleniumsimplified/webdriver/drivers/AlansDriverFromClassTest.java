package com.seleniumsimplified.webdriver.drivers;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * cover nuances with the Chrome AlansDriver
 */
public class AlansDriverFromClassTest {

    // As of Chrome v35, it reports an error regarding --ignore-certificate-errors
    // to remove this error start the chrome driver with
    // args "test-type"

    public static WebDriver driver;


    @AfterEach
    public void quitDriver(){

        driver.quit();
    }

    @Test
    public void defaultDriverUsage(){

        driver = Driver.getDriver();
        System.out.println(System.getenv());
        driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        assertThat(driver.getTitle(), is("HTML Form Elements"));
    }
    @Test
    public void basicEdgeDriverUsage(){
        driver = Driver.getDriver();

        driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        assertThat(driver.getTitle(), is("HTML Form Elements"));
    }

    @Test
    public void basicChromeDriverUsage(){
        driver = Driver.getDriver();

        driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        assertThat(driver.getTitle(), is("HTML Form Elements"));
    }

}

