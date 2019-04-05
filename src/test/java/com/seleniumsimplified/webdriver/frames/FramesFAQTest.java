package com.seleniumsimplified.webdriver.frames;

import com.seleniumsimplified.webdriver.manager.Browsers;
import com.seleniumsimplified.webdriver.manager.Driver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FramesFAQTest {

    private static WebDriver driver;

    @BeforeAll
    public static void startDriver(){
        driver = Driver.getDriver(Browsers.GOOGLECHROME);
        System.out.println(Driver.getBrowserName());
    }

    @BeforeEach
    public void setUp(){
        driver.get("http://www.compendiumdev.co.uk/selenium/frames");
    }
    @AfterAll
    public static void quitDriver() {
        driver.quit();
    }
    @Test
    public void whatHappensIfIDoNotSwitchTo(){
        assertEquals("Frameset Example Title (Example 6)",driver.getTitle());

        // If you remove the switchTo then it won't find the element below
//        driver.switchTo().frame("menu");
//        driver.findElement(By.cssSelector("a[href='frames_example_1.html']")).click();

        try{
            WebElement menuFramesExample1Element = driver.findElement(By.cssSelector("a[href='frames_example_1.html']"));
            menuFramesExample1Element.click();
            fail("I did not expect this step to run. Since I didn't expected above step to find this");

        }catch(NoSuchElementException e){
            // ignore the exception we expected
            e.printStackTrace();
        }
    }

}
