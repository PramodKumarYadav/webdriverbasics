package com.seleniumsimplified.webdriver.userinteractions;

import com.seleniumsimplified.webdriver.manager.Browsers;
import com.seleniumsimplified.webdriver.manager.Driver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class UserInteractionsExercisesTest {


    public static WebDriverWait wait;
    static WebDriver driver;

    @BeforeAll
    public static void startDriver(){
        driver = Driver.getDriver(Browsers.GOOGLECHROME);
        wait = new WebDriverWait(driver, 10);
    }
    @BeforeEach
    public void setUp(){
        driver.get("http://compendiumdev.co.uk/selenium/gui_user_interactions.html");

        driver.navigate().refresh();

    }
    @AfterAll
    public static void quitBrowser(){
        driver.quit();
    }

    @Test
    public void moveDraggableToDroppable() {
        WebElement draggable1 = driver.findElement(By.cssSelector("#draggable1"));
        WebElement dropppable1 = driver.findElement(By.cssSelector("#droppable1"));

        Actions actions = new Actions(driver);

        Action action = actions
                .clickAndHold(draggable1)
                .moveToElement(dropppable1)
                .release()
                .build();
        action.perform();

        assertEquals("Dropped!",dropppable1.getText());
    }
    @Test
    public void changeDraggable1TextUsingControlB(){
        WebElement draggable2 = driver.findElement(By.id("draggable2"));
        WebElement droppable1 = driver.findElement(By.id("droppable1"));

        Actions actions = new Actions(driver);
        actions
                .dragAndDrop(draggable2,droppable1)
                .perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#droppable1")));
        assertEquals("Get Off Me!", droppable1.getText());
    }
    @Test
    public void moveDraggable2ToDroppable1(){
        WebElement draggable1 = driver.findElement(By.cssSelector("#draggable1"));

        Actions actions = new Actions(driver);

        actions
                .keyDown(Keys.CONTROL)
                .sendKeys("b")
                .keyUp(Keys.CONTROL)
                .perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#droppable1")));
        assertEquals("Bwa! Ha! Ha!", draggable1.getText());

    }

    @Test
    public void changeDroppableBoxesTextUsingControlAndSpace(){

        WebElement droppable1 = driver.findElement(By.id("droppable1"));
        WebElement droppable2 = driver.findElement(By.id("droppable2"));

        Actions actions = new Actions(driver);

        // As per Alan this doesnt work. Reason is sendKeys on space does both down+up actions. Alternatively, we
        // can not do a keyDown on Space key since it is only allowed for keys like Alt,Control, Shift.
        actions
                .keyDown(Keys.CONTROL)
                .sendKeys(Keys.SPACE)
                .perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#droppable1")));
        assertEquals("Let GO!!!", droppable1.getText());
        assertEquals("Let GO!!!", droppable2.getText());

    }
    @Test
    public void drawInCanvas(){

        WebElement canvas = driver.findElement(By.cssSelector("canvas[id=canvas]"));

        WebElement eventList = driver.findElement(By.cssSelector("#keyeventslist"));

        int eventCount = eventList.findElements(By.tagName("li")).size();

        Actions actions = new Actions(driver);

        actions
                .clickAndHold(canvas)
                .moveByOffset(10,10)
                .moveByOffset(20,10)
                .moveByOffset(30,10)
                .release()
                .perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#droppable1")));
        assertTrue(eventCount < eventList.findElements(By.tagName("li")).size() );

    }
}
