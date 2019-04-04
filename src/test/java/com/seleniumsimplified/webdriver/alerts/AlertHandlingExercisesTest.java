package com.seleniumsimplified.webdriver.alerts;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertHandlingExercisesTest {

    private static WebDriver driver;


    @BeforeAll
    public static void setup(){
        driver = Driver.get("http://compendiumdev.co.uk/selenium/" + "alerts.html");
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
    @Test
    public void confirmBoxAlertAcceptTest(){

        WebElement alertButton;

        alertButton = driver.findElement(By.id("confirmexample"));

        alertButton.click();

        String alertMessage = "I am a confirm alert";

        assertEquals(alertMessage,driver.switchTo().alert().getText());

        driver.switchTo().alert().accept();

        String confirmReturnValue = driver.findElement(By.id("confirmreturn")).getText();
        assertEquals("true",confirmReturnValue);

    }

    @Test
    public void confirmBoxAlertDismissTest(){

        WebElement alertButton;

        alertButton = driver.findElement(By.id("confirmexample"));

        alertButton.click();

        String alertMessage = "I am a confirm alert";

        assertEquals(alertMessage,driver.switchTo().alert().getText());

        driver.switchTo().alert().dismiss();
        String confirmReturnValue = driver.findElement(By.id("confirmreturn")).getText();
        assertEquals("false",confirmReturnValue);
    }
    @Test
    public void promptBoxChangeValueDismissTest(){

        WebElement alertButton;

        alertButton = driver.findElement(By.id("promptexample"));

        alertButton.click();

        String alertMessage = "I prompt you";

        assertEquals(alertMessage,driver.switchTo().alert().getText());

        driver.switchTo().alert().dismiss();
        String confirmReturnValue = driver.findElement(By.id("promptreturn")).getText();
        assertEquals("pret",confirmReturnValue);
    }

    @Test
    public void promptBoxChangeValueAcceptTest(){

        WebElement alertButton;

        alertButton = driver.findElement(By.id("promptexample"));

        alertButton.click();

        String alertMessage = "I prompt you";

        assertEquals(alertMessage,driver.switchTo().alert().getText());

        driver.switchTo().alert().accept();
        String confirmReturnValue = driver.findElement(By.id("promptreturn")).getText();
        assertEquals("change me",confirmReturnValue);
    }

    @Test
    public void promptBoxChangeValueChangeValueAcceptTest(){

        WebElement alertButton;

        alertButton = driver.findElement(By.id("promptexample"));

        alertButton.click();

        String alertMessage = "I prompt you";

        assertEquals(alertMessage,driver.switchTo().alert().getText());

        Alert promptAlert = driver.switchTo().alert();
        promptAlert.sendKeys("pramod");
        promptAlert.accept();

        WebElement promptResult = driver.findElement(By.id("promptreturn"));
        assertEquals("pramod",promptResult.getText());
    }

    @Test
    public void promptBoxChangeValueChangeValueDismissTest(){

        WebElement alertButton;

        alertButton = driver.findElement(By.id("promptexample"));

        alertButton.click();

        String alertMessage = "I prompt you";

        assertEquals(alertMessage,driver.switchTo().alert().getText());

        Alert promptAlert = driver.switchTo().alert();
        promptAlert.sendKeys("pramod");
        promptAlert.dismiss();

        WebElement promptResult = driver.findElement(By.id("promptreturn"));
        assertEquals("pret",promptResult.getText());
    }
}

