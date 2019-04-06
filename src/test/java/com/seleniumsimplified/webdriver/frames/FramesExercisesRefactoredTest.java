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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FramesExercisesRefactoredTest {

    private static WebDriver driver;
    private WebDriverWait wait;

    // Using page factory
    @FindBy(how=How.CSS, using="body > h1")
    private WebElement bodyHeading; // =  driver.findElement(By.cssSelector("body > h1"));

    @FindBy(how=How.CSS, using="a[href='green.html']")
    private WebElement hrefGreenPage ;  // =  driver.findElement(By.cssSelector("a[href='green.html']"));

    @BeforeAll
    public static void startDriver(){
        driver = Driver.getDriver(Browsers.GOOGLECHROME);
    }

    @BeforeEach
    public void setUp(){
        driver.get("http://www.compendiumdev.co.uk/selenium/frames");
        wait = new WebDriverWait(driver, Driver.DEFAULT_TIMEOUT_SECONDS);

        PageFactory.initElements(driver,this);
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

        assertEquals("Content",bodyHeading.getText());

        assertEquals("Load green page",hrefGreenPage.getText());

        hrefGreenPage.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#green")));

        WebElement bodyHeadingGreenPage =  driver.findElement(By.cssSelector("#green"));
        assertEquals("Green Page",bodyHeadingGreenPage.getText());

        WebElement hrefOriginalPage =  driver.findElement(By.cssSelector("a[href='content.html']"));
        assertEquals("Back to original page",hrefOriginalPage.getText());

        hrefOriginalPage.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[.='Content']")));

        bodyHeading =  driver.findElement(By.cssSelector("body > h1"));
        assertEquals("Content",bodyHeading.getText());

        String titleDefaultContent = "Frameset Example Title (Example 6)";
        driver.switchTo().defaultContent();

        wait.until(ExpectedConditions.titleIs(titleDefaultContent));

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

        wait.until(ExpectedConditions.titleIs("HTML Frames Example - iFrame Contents"));

        driver.switchTo().frame(0);

        WebElement hrefIFramesEx05 =  driver.findElement(By.cssSelector("a[href='frames_example_5.html']"));
        assertEquals("Example 5",hrefIFramesEx05.getText());

        hrefIFramesEx05.click();

        driver.switchTo().frame("content");

        WebElement hrefIndex =  driver.findElement(By.cssSelector("a[href='index.html']"));
        assertEquals("Load Main Frames Page",hrefIndex.getText());

        hrefIndex.click();

        driver.switchTo().defaultContent();

        String titleDefaultContent = "Frameset Example Title (Example 6)";
        wait.until(ExpectedConditions.titleIs(titleDefaultContent));

        assertEquals(titleDefaultContent,driver.getTitle());
    }
}
