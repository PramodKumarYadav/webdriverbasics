package com.seleniumsimplified.webdriver.manipulation;

import com.seleniumsimplified.webdriver.manager.Browsers;
import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManipulateExercisesSelectRadio2Test {

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
    @AfterAll
    public static void quitBrowser(){
        driver.quit();
    }
    @Test
    public void submitWithRadio2ButtonSelected(){

        WebElement radioButton = driver.findElement(By.cssSelector("input[type='radio'][name='radioval'][value='rd2']"));
        radioButton.click();

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.click();

        WebElement radioButtonCurrentValue = driver.findElement(By.cssSelector("#_valueradioval"));
        assertEquals("rd2", radioButtonCurrentValue.getText());
    }
}

