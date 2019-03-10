package com.seleniumsimplified.webdriver.interrogation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InterrogationExampleTest {

    private static final String PROTOCOL = "http";
    private static final String DOMAIN = "www.compendiumdev.co.uk";
    private  String hostURL = PROTOCOL + "://" + DOMAIN ;

    private static WebDriver driver;

    @BeforeAll
    public static void createDriver() {

        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
//        driver = new InternetExplorerDriver();
//        driver = new OperaDriver();
//        driver = new SafariDriver();

    }

    @AfterAll

    public static void quitDriver() {
        driver.quit();
    }

    @Test
    public void assertTitleUsingJunit() throws MalformedURLException {

        String endPoint = "/selenium/basic_web_page.html";
        URL searchPage  = new URL(PROTOCOL, DOMAIN, endPoint);

        // Visit homepage
        driver.navigate().to(searchPage);

        // Assert title
        assertEquals("Basic Web Page Title", driver.getTitle());
        assertEquals(searchPage.toString(),driver.getCurrentUrl());
        assertTrue(driver.getPageSource().contains("A paragraph of text"),"Page should contain para 'A paragraph of text' ");

        System.out.println(driver.getPageSource());
    }

    @Test
    public void assertTitleUsingHamcrestMatchers() throws MalformedURLException {

        String endPoint = "/selenium/basic_web_page.html";
        URL searchPage  = new URL(PROTOCOL, DOMAIN, endPoint);

        driver.navigate().to(searchPage);

        assertThat(driver.getTitle(), is("Basic Web Page Title"));
        assertThat(driver.getCurrentUrl(), is(searchPage.toString()));
        assertThat(driver.getPageSource(), containsString("A paragraph of text"));
    }
}
