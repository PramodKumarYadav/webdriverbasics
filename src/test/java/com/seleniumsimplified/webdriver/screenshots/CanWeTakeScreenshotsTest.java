package com.seleniumsimplified.webdriver.screenshots;


import com.seleniumsimplified.webdriver.manager.AlansDriver;

import org.junit.jupiter.api.*;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.fail;

public class CanWeTakeScreenshotsTest {



    /*
    Because these tests change the driver, when run from IDE
    We want to remember the current driver and restore after all tests are run
 */
    private static AlansDriver.BrowserName rememberDriver;

    @BeforeAll
    public static void storeCurrentBrowser(){
        rememberDriver = AlansDriver.currentDriver;
    }

    @AfterAll
    public static void restoreDriver(){
        AlansDriver.set(rememberDriver);
    }

    @BeforeEach
    public void configureBrowser(){
        // early versions of these examples used to set the browser to Firefox
        // 20180611 I don't really see the point in that now that most browsers can take screenshots
        // and the tests have a guard to check if the capability is present

        // uncomment this line if you want to use firefox
        //AlansDriver.set(AlansDriver.BrowserName.FIREFOX);
    }
    @AfterEach
    public void quitDriver(){
        AlansDriver.quit();
    }
    @Test
    public void canWeTakeAScreenshotCapabilitiesStyle(){

        WebDriver driver = AlansDriver.get("http://seleniumsimplified.com");

        if(((HasCapabilities)driver).getCapabilities().is(CapabilityType.TAKES_SCREENSHOT)){
            TakesScreenshot snapper = (TakesScreenshot)driver;
            File tempImageFile = snapper.getScreenshotAs(OutputType.FILE);

            assertThat(tempImageFile.length(), is(greaterThan(0L)));

            // use these lines in debug mode
            System.out.println("Temp file written to " + tempImageFile.getAbsolutePath());
            AlansDriver.get("File://"+ tempImageFile.getAbsolutePath());
        }else{
            fail("AlansDriver did not support screenshots");
        }
    }

    @Test
    public void canWeTakeAScreenshotExceptionStyle(){

        WebDriver driver = AlansDriver.get("http://seleniumsimplified.com");

        try{
            TakesScreenshot snapper = (TakesScreenshot)driver;
            File tempImageFile = snapper.getScreenshotAs(OutputType.FILE);

            assertThat(tempImageFile.length(), is(greaterThan(0L)));

            // use these lines in debug mode
            System.out.println("Temp file written to " + tempImageFile.getAbsolutePath());
            AlansDriver.get("File://"+ tempImageFile.getAbsolutePath());

        }catch(ClassCastException e){
            // if we cannot cast it to TakesScreenshot we will get a ClassCastException
            System.out.println(e);
            fail("AlansDriver did not support screenshots");
        }
    }

    @Test
    public void htmlUnitDoesNotDoScreenshotsViaCapabilities(){
        AlansDriver.set(AlansDriver.BrowserName.HTMLUNIT);
        // using a different page because sometimes HTMLUnit driver doesn't like the javascript
        WebDriver driver = AlansDriver.get("http://compendiumdev.co.uk/selenium/testpages/find_by_playground.php");

        HasCapabilities capabilityDriver = (HasCapabilities)driver;

        if(capabilityDriver.getCapabilities().is(CapabilityType.TAKES_SCREENSHOT)){
            fail("Expected htmlunit to report false for takes_screenshot");
        }
    }

    @Test
    public void htmlUnitDoesNotDoScreenshotsViaException(){
        AlansDriver.set(AlansDriver.BrowserName.HTMLUNIT);
        // using a different page because sometimes HTMLUnit driver doesn't like the javascript
        WebDriver driver = AlansDriver.get("http://compendiumdev.co.uk/selenium/testpages/find_by_playground.php");

        try{
            TakesScreenshot snapper = (TakesScreenshot)driver;
            fail("Expected htmlunit to not cast to takes_screenshot");

        }catch(ClassCastException e){
            // if we cannot cast it to TakesScreenshot we will get a ClassCastException
            System.out.println(e);
        }
    }



}
