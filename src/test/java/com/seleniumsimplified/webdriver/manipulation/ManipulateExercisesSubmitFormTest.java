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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManipulateExercisesSubmitFormTest {

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
    public void submitFormAndAssertPageTitleChanges(){
        String homePageTitle = driver.getTitle();
        assertEquals("HTML Form Elements", homePageTitle);

        WebElement submitButton = driver.findElement(By.cssSelector("input[name='submitbutton'][type='submit']"));
        submitButton.click();

        String processedForm = driver.getTitle();
        assertEquals("Processed Form Details", processedForm);
    }
    @Test
    public void submitFormAtFormLevelAndAssertPageTitleChanges(){
        String homePageTitle = driver.getTitle();
        assertEquals("HTML Form Elements", homePageTitle);

        WebElement passwordInput = driver.findElement(By.cssSelector("input[type='password'][name='password']"));
        passwordInput.submit();

        String processedForm = driver.getTitle();
        assertEquals("Processed Form Details", processedForm);
    }
    @Test
    public void submitFormUsingKeysAndAssertPageTitleChanges(){
        String homePageTitle = driver.getTitle();
        assertEquals("HTML Form Elements", homePageTitle);

        WebElement passwordInput = driver.findElement(By.cssSelector("input[type='password'][name='password']"));
        passwordInput.sendKeys(Keys.ENTER);  // Another way.

        String processedForm = driver.getTitle();
        assertEquals("Processed Form Details", processedForm);
    }

}

