package com.seleniumsimplified.webdriver.interrogation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebElementInterrogationBasicsTest {

    private static final String PROTOCOL = "http";
    private static final String DOMAIN = "www.compendiumdev.co.uk";
    private  String hostURL = PROTOCOL + "://" + DOMAIN ;

    private static WebDriver driver;

    @BeforeAll
    public static void createDriver() {

        driver = new ChromeDriver();
    }

    @AfterAll

    public static void quitDriver() {

        driver.quit();
    }

    @Test
    public void assertTextInWebElement() throws MalformedURLException {

        String endPoint = "/selenium/basic_web_page.html";
        URL searchPage  = new URL(PROTOCOL, DOMAIN, endPoint);

        // Visit homepage
        driver.navigate().to(searchPage);

        WebElement para1 = driver.findElement(By.id("para1"));

        assertThat(para1.getText(),is("A paragraph of text"));
        assertEquals(para1.getText(),"A paragraph of text");

    }

    @Test
    public void exploreOptionsInWebElements() throws MalformedURLException {

        String endPoint = "/selenium/basic_web_page.html";
        URL searchPage  = new URL(PROTOCOL, DOMAIN, endPoint);

        driver.navigate().to(searchPage);

        WebElement para1 = driver.findElement(By.id("para1"));

        System.out.println(para1.getText());
        System.out.println(para1.getLocation());
        System.out.println(para1.getRect());
        System.out.println(para1.getSize());
        System.out.println(para1.getTagName());
        System.out.println(para1.isDisplayed());
        System.out.println(para1.isEnabled());
        System.out.println(para1.isSelected());
    }
}
