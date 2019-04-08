package com.seleniumsimplified.webdriver.windows;

import com.seleniumsimplified.webdriver.manager.Browsers;
import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WindowsManageExerciseTest {
    private WebDriver driver;

    @BeforeEach
    public void quitToRestart(){

        driver = Driver.getDriver(Browsers.GOOGLECHROME);
    }
    @BeforeEach
    public void setUp() {

        driver.get("http://www.compendiumdev.co.uk/selenium/bounce.html");
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
    public void maximizeWindow(){

        driver.manage().window().maximize();

        driver.manage().window().setSize(new Dimension(500,400));

        driver.manage().window().maximize();

    }

    @Test
    public void reduceWindowToHalfSize(){

        driver.manage().window().maximize();

        Dimension windowSize =  driver.manage().window().getSize();
        System.out.println(windowSize.getWidth());
        System.out.println(windowSize.getHeight());

        int maxWidth = windowSize.getWidth();
        int maxHeight = windowSize.getHeight();

        driver.manage().window().setSize(new Dimension(maxWidth/2,maxHeight/2));

        windowSize =  driver.manage().window().getSize();
        System.out.println(windowSize.getWidth());
        System.out.println(windowSize.getHeight());

        assertEquals(maxWidth/2,driver.manage().window().getSize().getWidth());
        assertEquals(maxHeight/2,driver.manage().window().getSize().getHeight());

    }

    @Test
    public void moveReducedWindowToCenterOfScreen(){

        driver.manage().window().maximize();

        Dimension windowSize =  driver.manage().window().getSize();
        int maxWidth = windowSize.getWidth();
        int maxHeight = windowSize.getHeight();

        driver.manage().window().setSize(new Dimension(maxWidth/2,maxHeight/2));
        driver.manage().window().setPosition(new Point(maxWidth/4,maxHeight/4));
    }
    @Test
    public void bounceTheWindow(){

        driver.manage().window().maximize();
        Dimension fullScreenSize = driver.manage().window().getSize();

        int changeWidth = fullScreenSize.getWidth();
        int changeHeight = fullScreenSize.getHeight();

        // 20180611 changed to use variables to make easier to amend
        int desiredWidth = 400;
        int desiredHeight = 300;

        while(changeWidth > desiredWidth){
            changeWidth = changeWidth - 50;

            if(changeHeight>desiredHeight)
                changeHeight = changeHeight - 50;

            System.out.println(String.format("Window size %d, %d", changeWidth, changeHeight));
            // potential bug in FireFox driver causes the setSize command to 'hang' when set too low
            // so on 20180611 changed the width size to allow this test to pass
            driver.manage().window().setSize(new Dimension(changeWidth, changeHeight));
            try {Thread.sleep(50);} catch (InterruptedException e) {}
        }

        int xDir = 10;
        int yDir = 10;

        Point position = driver.manage().window().getPosition();

        // original examples used 1000 for MAX_ITERATIONS
        // 1000 seems too long for CI, so dropped it down to 50 to make it faster
        int MAX_ITERATIONS = 50;

        for(int bounceIterations = 0; bounceIterations < MAX_ITERATIONS; bounceIterations ++){

            position = position.moveBy(xDir,yDir);

            driver.manage().window().setPosition(position);

            if(position.getX()>(fullScreenSize.getWidth() - changeWidth)){
                xDir = -10;
            }
            if(position.getX()<0){
                xDir = 10;
            }

            if(position.getY()>(fullScreenSize.getHeight() - changeHeight)){
                yDir = -10;
            }
            if(position.getY()<0){
                yDir = 10;
            }
        }

    }
}
