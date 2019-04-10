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

        Action action = actions.clickAndHold(draggable1).moveToElement(dropppable1).release().build();
        action.perform();

        assertEquals("Dropped!",dropppable1.getText());
    }
    @Test
    public void moveDraggable2ToDroppable1(){
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
    public void moveDraggable1ToDroppable1(){
        WebElement draggable1 = driver.findElement(By.id("draggable1"));
        WebElement droppable1 = driver.findElement(By.id("droppable1"));

        Actions actions = new Actions(driver);
        actions
                .dragAndDrop(draggable1,droppable1)
                .perform();

        assertEquals("Dropped!", droppable1.getText());

    }

    @Test
    public void dragAndDropDraggable2ToDroppable1(){
        WebElement draggable2 = driver.findElement(By.id("draggable2"));
        WebElement droppable1 = driver.findElement(By.id("droppable1"));

        Actions actions = new Actions(driver);

        // old code had a bug where I released after the drag and drop,
        // that now throws an error so removed spurious release
        actions.dragAndDrop(draggable2,droppable1).perform();

        assertEquals("Get Off Me!", droppable1.getText());

    }


    @Test
    public void controlAndSpace(){
        /*
            when I press control+space the red squares say "Let GO!!"
            we can't check this
         */
        WebElement droppable1 = driver.findElement(By.id("droppable1"));

        Actions actions = new Actions(driver);
        actions.click(droppable1).build().perform();
        // sendkeys does a keydown followed by keyup, so you can't use it for this
        // as keys need to be held down
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.SPACE).build().perform();
        String dropText = droppable1.getText();
        actions.keyUp(droppable1,Keys.CONTROL).build().perform();

//        try{
//            assertEquals("Let GO!!", dropText);
//            fail("send keys should not be held down long enough to get the text");
//        }catch(ComparisonFailure e){
//            assertTrue("How can we hold down the keys?",true);
//            assertEquals("Drop Here", dropText);
//        }
    }


    @Test
    public void controlAndBwaHaHa(){
        /* when we issue a control+ B draggable 1 says "Bwa! Ha! Ha! */

        WebElement draggable1 = driver.findElement(By.id("draggable1"));

        Actions actions = new Actions(driver);

        draggable1.click();

        new Actions(driver).keyDown(Keys.CONTROL).
                sendKeys("b").
                keyUp(Keys.CONTROL).
                perform();

        assertEquals("Bwa! Ha! Ha!", draggable1.getText());

        // firefox used to fail on this when it did a keyup after every keyDown
    }

    @Test
    public void drawSomethingOnCanvas(){
        WebElement canvas = driver.findElement(By.id("canvas"));
        WebElement eventList = driver.findElement(By.id("keyeventslist"));

        int eventCount = eventList.findElements(By.tagName("li")).size();

        new Actions(driver).
                // click doesn't do it, need to click and hold
                //click(canvas).
                        clickAndHold(canvas).
                moveByOffset(10,10).
                release().
                perform();

        assertTrue(eventCount < eventList.findElements(By.tagName("li")).size(),"we should have had some draw events");

    }
}
