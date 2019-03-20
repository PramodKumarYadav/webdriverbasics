package com.seleniumsimplified.webdriver.manipulation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManipulationBasicClickTest {

    private static final String PROTOCOL = "http";
    private static final String DOMAIN = "www.compendiumdev.co.uk";
    private String hostURL = PROTOCOL + "://" + DOMAIN;

    static WebDriver driver;

    WebDriverWait wait;

    @BeforeAll
    public static void createDriver() {

        driver = new ChromeDriver();
    }

    @AfterAll
    public static void quitDriver() {

        driver.quit();
    }

    @BeforeEach
    public void setupTestURL() throws MalformedURLException {

        String endPoint = "/selenium/basic_ajax.html";
        URL url = new URL(PROTOCOL, DOMAIN, endPoint);

        driver.navigate().to(url);

        // Create a default wait
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstManipulation() {

        WebElement element = driver.findElement(By.cssSelector("#combo1 > option:nth-child(3)"));
        System.out.println(element.getText());
        element.click();

        // Wait for element to be available
        // Note: Not using non static import means, you need to know below method to write it.
        // One of the way could be to import both static and non-static class.
        // Non - static to find all options. Static to make it more readable.
        wait.until(presenceOfElementLocated(By.cssSelector("body > form > #combo2 > option:nth-child(4)")));

        // #combo2 > option:nth-child(4) body > form
        element = driver.findElement(By.cssSelector("body > form > #combo2 > option:nth-child(4)"));
        System.out.println(element.getText());
        element.click();

        // Wait for element to be clickable
        // Note: Using expected conditions will give you all the options that you can use.
        wait.until(ExpectedConditions.elementToBeClickable(By.name("submitbutton")));

        element = driver.findElement(By.name("submitbutton"));
        System.out.println(element.getText());
        element.click();

        // Wait for element to be visibile
        wait.until(visibilityOfElementLocated(By.cssSelector("#_valuelanguage_id")));

        element = driver.findElement(By.cssSelector("#_valuelanguage_id"));
        System.out.println(element.getText());
        element.click();

        assertThat(driver.findElement(By.cssSelector("#_valuelanguage_id")).getText(), is("23"));
    }

}