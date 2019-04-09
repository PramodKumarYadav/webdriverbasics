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
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManipulateExercisesMultiSelectUsingSelectTest {

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
    public void submitWithDefaultOptionSelected(){

        submitForm();

        WebElement multiSelectItems = driver.findElement(By.cssSelector("#_valuemultipleselect0"));
        assertEquals("ms4", multiSelectItems.getText());
    }

    @Test
    public void submitWithShiftSelectOptionsSelected(){

        WebElement selectMultiple = driver.findElement(By.cssSelector("select[multiple='multiple']"));

        Select select = new Select(selectMultiple);
        assertEquals(true,select.isMultiple());

        List<WebElement> selectedElements = select.getAllSelectedOptions();
        assertEquals(1,selectedElements.size());
        assertEquals("Selection Item 4",selectedElements.get(0).getText().trim());

        select.deselectAll();
        selectedElements = select.getAllSelectedOptions();
        assertEquals(0,selectedElements.size());

        select.selectByIndex(0);
        select.selectByVisibleText("Selection Item 2");
        select.selectByValue("ms3");

        selectedElements = select.getAllSelectedOptions();
        assertEquals(3,selectedElements.size());

        submitForm();

        assertFirst3Selects();
    }

    private void submitForm() {
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.click();
    }
    private void assertFirst3Selects() {
        WebElement multiSelectItems01 = driver.findElement(By.cssSelector("#_valuemultipleselect0"));
        assertEquals("ms1", multiSelectItems01.getText());

        WebElement multiSelectItems02 = driver.findElement(By.cssSelector("#_valuemultipleselect1"));
        assertEquals("ms2", multiSelectItems02.getText());

        WebElement multiSelectItems03 = driver.findElement(By.cssSelector("#_valuemultipleselect2"));
        assertEquals("ms3", multiSelectItems03.getText());
    }

}

