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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManipulateExercisesSelectDropDownTest {

    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeAll
    public static void startDriver(){
        driver = Driver.getDriver(Browsers.GOOGLECHROME);
    }
    @BeforeEach
    public void setUp(){
        driver.get("http://www.compendiumdev.co.uk" +
                "/selenium/basic_html_form.html");

        wait = new WebDriverWait(driver,10);
    }
    @AfterAll
    public static void quitBrowser(){
        driver.quit();
    }

    @Test
    public void submitWithDropDownItem5Selected(){

        WebElement dropDownItem05 = driver.findElement(By.cssSelector("select[name='dropdown']>option[value='dd5']"));
        dropDownItem05.click();

        submitForm();
        assertDropDownValue05();
    }

    @Test
    public void submitWithDropDownItem5SelectedUsingSelectOption(){

        WebElement dropDownSelect = driver.findElement(By.cssSelector("select[name='dropdown']"));
        WebElement dropDownOption05 = dropDownSelect.findElement(By.cssSelector("option[value='dd5']"));
        dropDownOption05.click();

        submitForm();
        wait.until(ExpectedConditions.titleIs("Processed Form Details"));
        assertDropDownValue05();
    }
    @Test
    public void submitWithDropDownItem5SelectedUsingKeysOption(){

        WebElement dropDownSelect = driver.findElement(By.cssSelector("select[name='dropdown']"));
        dropDownSelect.sendKeys(Keys.chord(
                Keys.HOME,
                Keys.ARROW_DOWN,
                Keys.ARROW_DOWN,
                Keys.ARROW_DOWN,
                Keys.ARROW_DOWN));

        submitForm();

        assertDropDownValue05();
    }
    // Helper methods
    private void submitForm() {
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.click();
    }
    private void assertDropDownValue05() {
        WebElement dropDownCurrentValue = driver.findElement(By.cssSelector("#_valuedropdown"));
        assertEquals("dd5", dropDownCurrentValue.getText());
    }

}

