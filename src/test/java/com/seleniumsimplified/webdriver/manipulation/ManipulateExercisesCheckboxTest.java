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

public class ManipulateExercisesCheckboxTest {

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
    private void submitForm() {
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.click();
    }
    @Test
    public void submitWithOnlyCheckBox1Selected(){

        WebElement checkBox01 = driver.findElement(By.cssSelector("input[type='checkbox'][name='checkboxes[]'][value='cb1']"));

        // Select checkbox if not already selected
        if (checkBox01.isSelected()){
            System.out.println("Checkbox already selected");
        }
        else{
            System.out.println("Selecting Checkbox now");
            checkBox01.click();
        }

        WebElement checkBox02 = driver.findElement(By.cssSelector("input[type='checkbox'][name='checkboxes[]'][value='cb2']"));
        // Unselect checkbox if already selected
        if (checkBox02.isSelected()){
            checkBox01.click();
        }

        WebElement checkBox03 = driver.findElement(By.cssSelector("input[type='checkbox'][name='checkboxes[]'][value='cb3']"));
        // Unselect checkbox if already selected
        if (checkBox03.isSelected()){
            checkBox03.click();
        }

        submitForm();

        WebElement checkBoxCurrentValue = driver.findElement(By.cssSelector("#_valuecheckboxes0"));
        assertEquals("cb1", checkBoxCurrentValue.getText());
    }

    @Test
    public void submitWithMultipleCheckBox1Selected(){

        WebElement checkBox01 = driver.findElement(By.cssSelector("input[type='checkbox'][name='checkboxes[]'][value='cb1']"));

        // Select checkbox if not already selected
        if (checkBox01.isSelected()){
            System.out.println("Checkbox already selected");
        }
        else{
            System.out.println("Selecting Checkbox now");
            checkBox01.click();
        }

        WebElement checkBox02 = driver.findElement(By.cssSelector("input[type='checkbox'][name='checkboxes[]'][value='cb2']"));
        // Unselect checkbox if already selected
        if (checkBox02.isSelected()){
            checkBox01.click();
        }

        WebElement checkBox03 = driver.findElement(By.cssSelector("input[type='checkbox'][name='checkboxes[]'][value='cb3']"));
        // Select checkbox if not already selected
        if (checkBox03.isSelected()){
            System.out.println("Checkbox already selected");
        }
        else{
            System.out.println("Selecting Checkbox now");
            checkBox03.click();
        }

        submitForm();

        WebElement checkBoxCurrentValue01 = driver.findElement(By.cssSelector("#_valuecheckboxes0"));
        assertEquals("cb1", checkBoxCurrentValue01.getText());

        WebElement checkBoxCurrentValue02 = driver.findElement(By.cssSelector("#_valuecheckboxes1"));
        assertEquals("cb3", checkBoxCurrentValue02.getText());
    }
}

