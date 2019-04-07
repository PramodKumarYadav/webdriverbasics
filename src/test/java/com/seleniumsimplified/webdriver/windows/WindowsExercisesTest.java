package com.seleniumsimplified.webdriver.windows;

import com.seleniumsimplified.webdriver.manager.Browsers;
import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WindowsExercisesTest {

    private WebDriver driver;

    @BeforeEach
    public void quitToRestart(){

        driver = Driver.getDriver(Browsers.GOOGLECHROME);
    }
    @BeforeEach
    public void setUp() {

        driver.get("http://www.compendiumdev.co.uk/selenium/frames");
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getWindowHandle());
    }
    @AfterEach
    public void quitToClean(){
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void switchToNewWindow(){

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

        driver.switchTo().window(framesWindowHandle);
        assertTrue(driver.getTitle().contains("Frameset Example Title (Example 6)"));

    }

    @Test
    public void switchBetweenWindows(){

        String framesWindowHandle = driver.getWindowHandle();
        assertEquals(1, driver.getWindowHandles().size(),"Expected only 1 current window");

        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='http://www.seleniumsimplified.com']")).click();
        driver.findElement(By.cssSelector("#goevil")).click();
        driver.findElement(By.cssSelector("a[href='http://www.compendiumdev.co.uk']")).click();


        assertEquals(4, driver.getWindowHandles().size(),"Expected a New Window opened");

        Set<String> openWindows = driver.getWindowHandles();
        System.out.println(openWindows);
        String newWindowHandle="";

        int counterWindowHandle = 1;
        for(String aHandle : openWindows){
            System.out.println("Window handle # " + counterWindowHandle + " : " + aHandle);

            driver.switchTo().window(aHandle);
            System.out.println(driver.getTitle());

            counterWindowHandle++;
        }

        driver.switchTo().window("compdev");
        System.out.println(driver.getTitle());
        assertEquals("Software Testing Consultancy, Books and Online Training",driver.getTitle());

    }

}
