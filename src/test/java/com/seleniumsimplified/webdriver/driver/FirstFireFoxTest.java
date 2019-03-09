package com.seleniumsimplified.webdriver.driver;

import org.junit.jupiter.api.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstFireFoxTest {

    @Test
    public void firefoxIsSupportedByWebdriver() {

        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.compendiumdev.co.uk/selenium");

        assertTrue(driver.getTitle().startsWith("Selenium Simplified"));
        driver.close();
    }
    @Test
    public void firefoxIsSupportedByWebdriverSetUpSystemProperty() {

        String currentDirectory = System.getProperty("user.dir");
        System.out.println(currentDirectory);

        // If using driver location next to current directory than use below.
        // String driverLocation = currentDirectory + "/../tools/drivers/geckodriver.exe"
        String driverLocation = "C:\\ProgramData\\chocolatey\\bin\\geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", driverLocation);

        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.compendiumdev.co.uk/selenium");

        assertTrue(driver.getTitle().startsWith("Selenium Simplified"));
        driver.close();
    }
}
