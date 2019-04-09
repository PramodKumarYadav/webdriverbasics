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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManipulateExercisesMultiSelectTest {

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
        selectMultiple.findElement(By.cssSelector("option[value=\"ms1\"]")).click();
        selectMultiple.sendKeys(Keys.SHIFT);
        selectMultiple.findElement(By.cssSelector("option[value=\"ms3\"]")).click();

        submitForm();

        WebElement multiSelectItems01 = driver.findElement(By.cssSelector("#_valuemultipleselect0"));
        assertEquals("ms1", multiSelectItems01.getText());

        WebElement multiSelectItems02 = driver.findElement(By.cssSelector("#_valuemultipleselect1"));
        assertEquals("ms3", multiSelectItems02.getText());

        WebElement multiSelectItems03 = driver.findElement(By.cssSelector("#_valuemultipleselect2"));
        assertEquals("ms4", multiSelectItems03.getText());
    }

    @Test
    public void submitWithControlOptionsSelected(){

        WebElement selectMultiple = driver.findElement(By.cssSelector("select[multiple='multiple']"));

        selectMultiple.findElement(By.cssSelector("option[value=\"ms1\"]")).click();
        selectMultiple.sendKeys(Keys.CONTROL);
        selectMultiple.findElement(By.cssSelector("option[value=\"ms3\"]")).click();

        submitForm();

        WebElement multiSelectItems01 = driver.findElement(By.cssSelector("#_valuemultipleselect0"));
        assertEquals("ms1", multiSelectItems01.getText());

        WebElement multiSelectItems02 = driver.findElement(By.cssSelector("#_valuemultipleselect1"));
        assertEquals("ms3", multiSelectItems02.getText());

        WebElement multiSelectItems03 = driver.findElement(By.cssSelector("#_valuemultipleselect2"));
        assertEquals("ms4", multiSelectItems03.getText());
    }
    @Test
    public void submitWithListOptionsSelected(){

        WebElement selectMultiple = driver.findElement(By.cssSelector("select[multiple='multiple']"));
        List<WebElement> multiSelectOptions = selectMultiple.findElements(By.tagName("option"));

        System.out.println(multiSelectOptions.size());

        multiSelectOptions.get(0).click();
        multiSelectOptions.get(1).click();
        multiSelectOptions.get(2).click();

        //Deselect option 4
        if (multiSelectOptions.get(3).isSelected()) {
            multiSelectOptions.get(3).click();
        }

        submitForm();

        WebElement multiSelectItems01 = driver.findElement(By.cssSelector("#_valuemultipleselect0"));
        assertEquals("ms1", multiSelectItems01.getText());

        WebElement multiSelectItems02 = driver.findElement(By.cssSelector("#_valuemultipleselect1"));
        assertEquals("ms2", multiSelectItems02.getText());

        WebElement multiSelectItems03 = driver.findElement(By.cssSelector("#_valuemultipleselect2"));
        assertEquals("ms3", multiSelectItems03.getText());

    }

    private void submitForm() {
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.click();
    }
}

