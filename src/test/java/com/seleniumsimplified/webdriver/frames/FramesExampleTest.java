package com.seleniumsimplified.webdriver.frames;

import com.seleniumsimplified.webdriver.manager.Browsers;
import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FramesExampleTest {

    private static WebDriver driver;

    @BeforeAll
    public static void startDriver(){
        driver = Driver.getDriver(Browsers.GOOGLECHROME);
    }

    @BeforeEach
    public void setUp(){
        driver.get("http://www.compendiumdev.co.uk/selenium/frames");
    }
    @AfterAll
    public static void quitDriver() {
        if(driver!=null){
            driver.quit();
        }

    }
    @Test
    public void switchToFrameExample(){

        assertEquals("Frameset Example Title (Example 6)",driver.getTitle());

        driver.switchTo().frame("menu");
        driver.findElement(By.cssSelector("a[href='frames_example_1.html']")).click();

        String titleDefaultContent = "Frameset Example Title (Example 1)";

        // added for Marionette Driver to force moving frame
        // not needed for other drivers but it does no harm for other drivers
        // Note - this is only needed if we are checking the title, not for
        // any other action
        driver.switchTo().defaultContent();
        new WebDriverWait(driver,Driver.DEFAULT_TIMEOUT_SECONDS).
                until(ExpectedConditions.titleIs(titleDefaultContent));

        assertEquals(titleDefaultContent,driver.getTitle());
    }
}
