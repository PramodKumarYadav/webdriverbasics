package com.seleniumsimplified.webdriver.manipulation;

import com.seleniumsimplified.webdriver.manager.Browsers;
import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManipulateExercisesCommentsTest {

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
    public void submitCommentWithDefaults(){

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.submit();

        WebElement commentsCurrentValue = driver.findElement(By.cssSelector("#_valuecomments"));
        assertEquals("Comments...", commentsCurrentValue.getText());
    }
    @Test
    public void clearCommentTypeAndSubmitForm(){
        String homePageTitle = driver.getTitle();
        assertEquals("HTML Form Elements", homePageTitle);

        WebElement commentsTextArea = driver.findElement(By.cssSelector("[name='comments']"));
        commentsTextArea.clear();
        commentsTextArea.sendKeys("Pramod Yadav");

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.click();

        WebElement commentsCurrentValue = driver.findElement(By.cssSelector("#_valuecomments"));
        assertEquals("Pramod Yadav", commentsCurrentValue.getText());
    }
}

