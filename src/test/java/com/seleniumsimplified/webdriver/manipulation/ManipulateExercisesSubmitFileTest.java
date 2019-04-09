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

public class ManipulateExercisesSubmitFileTest {

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
    public void submitWithNoFileAsDefaults(){

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.submit();

        WebElement commentsCurrentValue = driver.findElement(By.cssSelector("#_valuecomments"));
        assertEquals("No Value for filename", commentsCurrentValue.getText());
    }
    @Test
    public void uploadFileAndAssertFileName(){

        WebElement chooseFileButton = driver.findElement(By.cssSelector("input[type='file'][name='filename'"));
        chooseFileButton.clear();
        chooseFileButton.sendKeys("C:\\Users\\Pramod Yadav\\Desktop\\readInput.png");

        submitForm();

        WebElement fileNameValue = driver.findElement(By.cssSelector("#_valuefilename"));
        assertEquals("readInput.png", fileNameValue.getText());
    }

    private void submitForm() {
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
        submitButton.click();
    }
}

