package com.seleniumsimplified.webdriver.remote;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class SauceLabsTest {

    public static WebDriver driver=null;

    @BeforeAll
    public static void setupSauce(){

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//        capabilities.setCapability("platform", Platform.MAC);
        capabilities.setCapability("platform", "macOS 10.14");
        capabilities.setCapability("version", "66.0");


//        DesiredCapabilities caps = DesiredCapabilities.chrome();
//        caps.setCapability("platform", "Windows 10");
//        caps.setCapability("version", "73.0");

        capabilities.setCapability("name", "Running remote test for " + Platform.MAC + " : " + capabilities.getBrowserName() +  " : " + capabilities.getVersion());
        capabilities.setCapability("tags", "Testing on OS");
        capabilities.setCapability("build", "build - 1234");

        try {
            // add url to environment variables to avoid releasing with source
            // sauceURL = "http://PramodKumarYadav:bdd64624-cfae-45aa-98b8-0975c0a02798@ondemand.saucelabs.com:80/wd/hub";
            String sauceURL = System.getenv("SAUCELABS_URL");
            System.out.println(sauceURL);

            driver = new RemoteWebDriver(
                    new URL(sauceURL),
                    capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    @AfterAll
    public static void stopSauce(){

        driver.quit();
    }
    @Test
    public void simpleInteraction(){
        driver.get("http://www.compendiumdev.co.uk" +
                "/selenium/basic_html_form.html");

        WebElement checkBox1 = driver.findElement(
                By.cssSelector("input[value='cb1']"));

        assertFalse(
                checkBox1.isSelected(),"Starts not selected");

        checkBox1.click();

        assertTrue(checkBox1.isSelected(),"Click selects");
    }

    @Test
    public void loadTheGreenPage(){
        driver.get("http://www.compendiumdev.co.uk/selenium/frames");

        WebDriverWait wait = new WebDriverWait(driver, Driver.DEFAULT_TIMEOUT_SECONDS);

        assertThat(driver.getTitle(), is("Frameset Example Title (Example 6)"));

        // load the green page
        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='green.html']")).click();

        wait.until(presenceOfElementLocated(By.cssSelector("h1[id='green']")));

        // click on "Back to original page"
        driver.findElement(By.cssSelector("a[href='content.html']")).click();

        // assert for presence of "<h1>Content</h1>"
        WebElement h1 = wait.until(presenceOfElementLocated(By.xpath("//h1[.='Content']")));

        assertThat(h1.getText(), is("Content"));
    }

    @Test
    public void workWithTheIFrame(){
        driver.get("http://www.compendiumdev.co.uk/selenium/frames");

        WebDriverWait wait = new WebDriverWait(driver,Driver.DEFAULT_TIMEOUT_SECONDS);

        assertThat(driver.getTitle(), is("Frameset Example Title (Example 6)"));

        // click on "menu"."iFrames Example"
        driver.switchTo().frame("menu");
        driver.findElement(By.cssSelector("a[href='iframe.html']")).click();

        wait.until(titleIs("HTML Frames Example - iFrame Contents"));

        // click on Example 5 in the iFrame
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector("a[href='frames_example_5.html']")).click();

        wait.until(titleIs("Frameset Example Title (Example 5)"));

        // then content.load main frames page
        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='index.html']")).click();

        wait.until(titleIs("Frameset Example Title (Example 6)"));
    }


}

