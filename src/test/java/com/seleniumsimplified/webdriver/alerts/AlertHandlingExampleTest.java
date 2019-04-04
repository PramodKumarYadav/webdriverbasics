package com.seleniumsimplified.webdriver.alerts;

import com.seleniumsimplified.webdriver.manager.Driver;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertHandlingExampleTest {

    private static WebDriver driver;


    @BeforeAll
    public static void setup(){
        driver = Driver.get("http://compendiumdev.co.uk/selenium/" + "alert.html");
    }

    @Test
    public void basicAlertHandlingExample(){

        WebElement alertButton;

        alertButton = driver.findElement(By.id("alertexamples"));

        alertButton.click();

        String alertMessage = "I am an alert box!";

        assertEquals(alertMessage,driver.switchTo().alert().getText());

        driver.switchTo().alert().accept();

    }

}

