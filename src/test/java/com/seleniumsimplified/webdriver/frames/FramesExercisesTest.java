package com.seleniumsimplified.webdriver.frames;

import com.seleniumsimplified.webdriver.manager.Browsers;
import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FramesExercisesTest {

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

        driver.switchTo().frame("content");

        WebElement bodyHeading =  driver.findElement(By.cssSelector("body > h1"));
        assertEquals("Content",bodyHeading.getText());

        WebElement hrefGreenPage =  driver.findElement(By.cssSelector("a[href='green.html']"));
        assertEquals("Load green page",hrefGreenPage.getText());

        hrefGreenPage.click();
        WebElement bodyHeadingGreenPage =  driver.findElement(By.cssSelector("#green"));
        assertEquals("Green Page",bodyHeadingGreenPage.getText());

        WebElement hrefOriginalPage =  driver.findElement(By.cssSelector("a[href='content.html']"));
        assertEquals("Back to original page",hrefOriginalPage.getText());

        hrefOriginalPage.click();
        bodyHeading =  driver.findElement(By.cssSelector("body > h1"));
        assertEquals("Content",bodyHeading.getText());

        String titleDefaultContent = "Frameset Example Title (Example 6)";
        driver.switchTo().defaultContent();
        new WebDriverWait(driver,Driver.DEFAULT_TIMEOUT_SECONDS).
                until(ExpectedConditions.titleIs(titleDefaultContent));

        assertEquals(titleDefaultContent,driver.getTitle());
    }

    @Test
    public void switchToiFrameExample(){

        assertEquals("Frameset Example Title (Example 6)",driver.getTitle());

        driver.switchTo().frame("menu");

        WebElement hrefIFrames =  driver.findElement(By.cssSelector("a[href='iframe.html']"));
        assertEquals("iFrames Example",hrefIFrames.getText());

        hrefIFrames.click();

        driver.switchTo().defaultContent();

        new WebDriverWait(driver,Driver.DEFAULT_TIMEOUT_SECONDS).
                until(ExpectedConditions.titleIs("HTML Frames Example - iFrame Contents"));

        driver.switchTo().frame(0);

        WebElement hrefIFramesEx05 =  driver.findElement(By.cssSelector("a[href='frames_example_5.html']"));
        assertEquals("Example 5",hrefIFramesEx05.getText());

        driver.switchTo().frame("menu");

        WebElement hrefIndex =  driver.findElement(By.cssSelector("a[href='index.html']"));
        assertEquals("Load Main Frames Page",hrefIndex.getText());

        String titleDefaultContent = "Frameset Example Title (Example 6)";
        driver.switchTo().defaultContent();
        new WebDriverWait(driver,Driver.DEFAULT_TIMEOUT_SECONDS).
                until(ExpectedConditions.titleIs(titleDefaultContent));

        assertEquals(titleDefaultContent,driver.getTitle());
    }
}
