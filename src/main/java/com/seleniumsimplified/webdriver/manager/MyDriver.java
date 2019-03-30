package com.seleniumsimplified.webdriver.manager;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MyDriver {

    private static WebDriver myDriver = null;

    private String browserName;

    public enum Browsers{FIREFOX, GOOGLECHROME, SAUCELABS, IE, HTMLUNIT, GRID, FIREFOXPORTABLE, FIREFOXMARIONETTE, APPIUM, EDGE, SAFARI}

    public MyDriver(){
        this("GOOGLECHROME");
    }
    private MyDriver(String browserName){
        this.browserName = browserName;
    }

    private String getBrowserName() {
        return browserName;
    }

    public static WebDriver getBrowser(String browserName) {

        switch (browserName) {

            case "FIREFOX":

                System.setProperty("webdriver.gecko.driver", "C:\\tools\\selenium\\geckodriver.exe");
                myDriver = new FirefoxDriver();
                break;

            case "HTMLUNIT":

                // HtmlUnitDriver added as a maven dependency - no paths required
                myDriver = new HtmlUnitDriver(true);  // enable javascript
                break;

            case "EDGE":

                System.setProperty("webdriver.edge.driver", "C:\\tools\\selenium\\MicrosoftWebDriver.exe");
                myDriver = new EdgeDriver();
                break;

            case "GOOGLECHROME":

                System.setProperty("webdriver.chrome.driver", "C:\\tools\\selenium\\chromedriver.exe");

                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                options.addArguments("disable-plugins");
                options.addArguments("disable-extensions");
                //  options.addExtensions(new File("/path/to/extension.crx"));  // If you want to add extensions.
                options.addArguments("test-type");
                myDriver = new ChromeDriver(options);
                break;
        }

        return myDriver;

    }
}