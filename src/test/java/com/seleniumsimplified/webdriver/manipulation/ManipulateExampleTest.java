package com.seleniumsimplified.webdriver.manipulation;

import com.seleniumsimplified.webdriver.manager.Browsers;
import com.seleniumsimplified.webdriver.manager.Driver;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManipulateExampleTest {

    static WebDriver driver;

    @BeforeAll
    public static void startDriver(){
        driver = Driver.getDriver(Browsers.GOOGLECHROME);
    }
    @BeforeEach
    public void setUp(){

        driver.get("http://www.compendiumdev.co.uk" +
                "/selenium/basic_html_form.html");
    }

    @Test
    public void simpleInteraction(){
        WebElement checkBox1 = driver.findElement(By.cssSelector("input[value='cb1']"));
        assertFalse(checkBox1.isSelected(),"Starts not selected");

        checkBox1.click();
        assertTrue(checkBox1.isSelected(),"Click selects");
    }


    @AfterAll
    public static void quitBrowser(){
        driver.quit();
    }
}

