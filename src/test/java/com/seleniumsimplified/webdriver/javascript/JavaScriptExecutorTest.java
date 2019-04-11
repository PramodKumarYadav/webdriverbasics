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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaScriptExecutorTest {

    private static WebDriver driver;

    @BeforeAll
    public static void startDriver(){

        driver = Driver.getDriver(Browsers.GOOGLECHROME);
    }
    @BeforeEach
    public void setUp(){

        driver.get("http://www.compendiumdev.co.uk/selenium/canvas_basic.html");
    }
    @AfterAll
    public static void quitBrowser(){

        driver.quit();
    }
    @Test
    public void passArgsToTheJavaScript(){

        JavascriptExecutor js =(JavascriptExecutor)driver;

        int actionsCount = driver.findElements(By.cssSelector("#commandlist li")).size();

        assertEquals(2, actionsCount);

        for(int testLoop=0; testLoop < 10; testLoop++){

            js.executeScript("draw(0, arguments[0], arguments[1], 20, arguments[2]);",
                    testLoop*20,
                    testLoop*20,
                    "#" + testLoop + testLoop + "0000");
        }

        actionsCount = driver.findElements(By.cssSelector("#commandlist li")).size();
        assertEquals(12, actionsCount);
    }

    @Test
    public void returnValuesFromJavaScript(){
        JavascriptExecutor js =(JavascriptExecutor)driver;

        assertEquals(
                40L,
                js.executeScript(
                        "return (arguments[0]+arguments[1]);",
                        20, 20),"Javascript should calc correctly");
    }

    @Test
    public void returnHardCodedValueFromJavaScript(){

        JavascriptExecutor js =(JavascriptExecutor)driver;
        assertEquals( 10L, js.executeScript("return 10;"),"return 10");
    }

    @Test
    public void changeTitleUsingJavascript(){

        JavascriptExecutor js =(JavascriptExecutor)driver;
        assertEquals("Javascript Canvas Example", driver.getTitle());

        js.executeScript("document.title=arguments[0]", "bob");
        assertEquals("bob", driver.getTitle());
    }

    @Test
    public void useJQueryToHideBodyWithNoParams(){

        JavascriptExecutor js =(JavascriptExecutor)driver;
        assertTrue(driver.findElement(By.cssSelector("#commands")).isDisplayed());

        js.executeScript("$('body').hide();");
        assertFalse(driver.findElement(By.cssSelector("#commands")).isDisplayed());
    }

    @Test
    public void hideWebElementAsParam(){

        JavascriptExecutor js =(JavascriptExecutor)driver;
        assertTrue(driver.findElement(By.cssSelector("#commands")).isDisplayed());

        js.executeScript("$(arguments[0]).hide();", driver.findElement(By.cssSelector("#commands")));
        assertFalse(driver.findElement(By.cssSelector("#commands")).isDisplayed());
    }

    @Test
    public void javascriptRunsAsAnAnonymousFunctionButWeCanLeaveSomeBehindOtherBrowsers(){
        JavascriptExecutor js =(JavascriptExecutor)driver;

        // In this example I'm not using alerts, I'm using title
        // this code run as an anonymous function with no trace left
        js.executeScript("document.title='title changed by webdriver';");
        assertThat(driver.getTitle(), is("title changed by webdriver"));


        // this code creates a function that will persist as we have added it to the global window
        js.executeScript("window.webdrivertitlechange = function(){document.title='stored title changed by webdriver';};"+
                "window.webdrivertitlechange.call();");

        assertThat(driver.getTitle(), is("stored title changed by webdriver"));

        // this can only work if we managed to leave javascript lying around
        js.executeScript("document.title='title changed by webdriver';");

        js.executeScript("window.webdrivertitlechange.call();");
        assertThat(driver.getTitle(), is("stored title changed by webdriver"));
    }
}

