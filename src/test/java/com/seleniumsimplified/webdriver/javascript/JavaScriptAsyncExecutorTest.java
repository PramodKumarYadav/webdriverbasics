package com.seleniumsimplified.webdriver.javascript;

import com.seleniumsimplified.webdriver.manager.Browsers;
import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class JavaScriptAsyncExecutorTest {


    private static WebDriver driver;

    @BeforeAll
    public static void startDriver(){
        driver = Driver.getDriver(Browsers.GOOGLECHROME);
    }

    @BeforeEach
    public void resetBeforeTest() {
        driver.get("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");
        driver.navigate().refresh();
    }

    @AfterAll
    public static void quitBrowser() {
        driver.quit();
    }
    // for hints see http://stackoverflow.com/questions/2857900/onhide-type-event-in-

    @Test
    public void syncOnAjaxGifRemovalViaAsync() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        js.executeScript("window.webdrivercallback = function(){};" +
                //extend the jQuery hide method to call our callback when it hides the gif
                "var _oldhide = $.fn.hide;" +
                "$.fn.hide = function(speed, callback) {" +
                "    var retThis = _oldhide.apply(this,arguments);" +
                "    window.webdrivercallback.apply();" +
                "    return retThis;" +
                "};"
        );

        // select Server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='3']")).click();

        // need to wait in here, can do it with async javascript
        js.executeAsyncScript("window.webdrivercallback = arguments[arguments.length - 1];");

        // then select Java
        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(By.cssSelector("option[value='23']")).click();

        // Submit the form
        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInIt.click();

        // don't have to synchronise with other browsers but do with GeckoDriver
        //WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        WebElement languageWeUsed = new WebDriverWait(driver, 10).until(elementToBeClickable(By.id("_valuelanguage_id")));
        assertEquals("23", languageWeUsed.getText(),"Expected Java code");
    }
}