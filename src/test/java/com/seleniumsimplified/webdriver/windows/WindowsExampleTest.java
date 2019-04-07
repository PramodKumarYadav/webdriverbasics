package com.seleniumsimplified.webdriver.windows;

import com.seleniumsimplified.webdriver.manager.Browsers;
import com.seleniumsimplified.webdriver.manager.Driver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WindowsExampleTest {

    private static WebDriver driver;

    @BeforeAll
    public static void quitToRestart(){

        driver = Driver.getDriver(Browsers.GOOGLECHROME);
    }

    @Test
    public void switchToNewWindow(){

       driver.get("http://www.compendiumdev.co.uk/selenium/frames");

        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getWindowHandle());

        String framesWindowHandle = driver.getWindowHandle();
        assertEquals(1, driver.getWindowHandles().size(),"Expected only 1 current window");

        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='http://www.seleniumsimplified.com']")).click();

        assertEquals(2, driver.getWindowHandles().size(),"Expected a New Window opened");
        System.out.println(driver.getWindowHandles());

        Set<String> myWindows = driver.getWindowHandles();
        String newWindowHandle="";

        int counterWindowHandle = 1;
        for(String aHandle : myWindows){
            System.out.println("Window handle # " + counterWindowHandle + " : " + aHandle);
            if(!framesWindowHandle.contentEquals(aHandle)){
                newWindowHandle = aHandle; break;
            }
            counterWindowHandle++;
        }

        driver.switchTo().window(newWindowHandle);

        // for Marionette Geckodriver need to switchTo defaultContent to check title
        driver.switchTo().defaultContent();

        assertTrue(driver.getTitle().contains("Selenium Simplified"),"Expected Selenium Simplified site");
    }

    @AfterAll
    public static void quitToClean(){

        driver.quit();
    }
}
