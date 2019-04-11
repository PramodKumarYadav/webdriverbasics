package com.seleniumsimplified.webdriver.manager;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Driver {

    private static WebDriver myDriver = null;

    // Select default browser (when user don't select one, or when you want to drive all tests from here)
    private static Browsers browserName = Browsers.HTMLUNIT;
    public static final long DEFAULT_TIMEOUT_SECONDS = 10;
    private static final String APPIUM_DEVICE_NAME = "";

    public static String getBrowserName() {
        return browserName.name();
    }
    public static WebDriver getDriver() {
        return getDriver(Browsers.HTMLUNIT);
    }
    public static WebDriver getDriver(Browsers browserName) {

        System.out.println("Creating driver for : " + browserName);
        switch (browserName.name()) {

            case "FIREFOX":

                System.setProperty("webdriver.gecko.driver", "C:\\tools\\selenium\\geckodriver.exe");
                myDriver = new FirefoxDriver();
                break;

            case "HTMLUNIT":  // HtmlUnitDriver added as a maven dependency - no paths required
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
            case "SAUCELABS":
                // I would like to make the below code more dynamic by adding list of below available items and ...
                // Randomly selected valid list from a random function to test it.
                DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();
                firefoxCapabilities.setCapability("version", "5");
                firefoxCapabilities.setCapability("platform", Platform.XP);
                try {
                    // add url to environment variables to avoid releasing with source
                    String sauceURL = System.getenv("SAUCELABS_URL");
                    myDriver = new RemoteWebDriver(
                            new URL(sauceURL),
                            firefoxCapabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case "APPIUM":

                // quick hack code to get appium running
                // only one env variable, your APPIUM_DEVICE_NAME
                // so amend code for your local version

                DesiredCapabilities appiumCapabilities = new DesiredCapabilities();

                // the device name can be seen when you do "adb devices"
                appiumCapabilities.setCapability("deviceName", APPIUM_DEVICE_NAME);
                appiumCapabilities.setCapability("platformName", Platform.ANDROID);
                appiumCapabilities.setCapability("app", browserName.name());
                try {
                    // add url to environment variables to avoid releasing with source
                    // Something like this : String appiumURL = System.getenv("APPIUM_URL");
                    String appiumURL = "http://127.0.0.1:4723/wd/hub";
                    myDriver = new RemoteWebDriver(
                            new URL(appiumURL),
                            appiumCapabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case "GRID":

                String gridBrowser = EnvironmentPropertyReader.getPropertyOrEnv("WEBDRIVER_GRID_BROWSER", "firefox");
                String gridBrowserVersion = EnvironmentPropertyReader.getPropertyOrEnv("WEBDRIVER_GRID_BROWSER_VERSION", "");
                String gridBrowserPlatform = EnvironmentPropertyReader.getPropertyOrEnv("WEBDRIVER_GRID_BROWSER_PLATFORM", "");

                DesiredCapabilities gridCapabilities = new DesiredCapabilities();
                gridCapabilities.setBrowserName(gridBrowser);
                if(gridBrowserVersion.length()>0)
                    gridCapabilities.setVersion(gridBrowserVersion);
                if(gridBrowserPlatform.length()>0)
                    gridCapabilities.setPlatform(Platform.fromString(gridBrowserPlatform));

                // Allow adding any capability defined as an environment variable
                // extra environment capabilities start with "WEBDRIVER_GRID_CAP_X_"

                // e.g. WEBDRIVER_GRID_CAP_X_os_version XP
                // e.g. WEBDRIVER_GRID_CAP_X_browserstack.debug true
                Map<String, String> anyExtraCapabilities = System.getenv();
                addAnyValidExtraCapabilityTo(gridCapabilities, anyExtraCapabilities.keySet());

                // Now check properties for extra capabilities
                Properties anyExtraCapabilityProperties = System.getProperties();
                addAnyValidExtraCapabilityTo(gridCapabilities, anyExtraCapabilityProperties.stringPropertyNames());


                try {
                    // add url to environment variables to avoid releasing with source
                    String gridURL = EnvironmentPropertyReader.getPropertyOrEnv("WEBDRIVER_GRID_URL", "http://localhost:4444/wd/hub");
                    myDriver = new RemoteWebDriver(new URL(gridURL), gridCapabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            default:

                // HtmlUnitDriver added as a maven dependency - no paths required
                myDriver = new HtmlUnitDriver(true);  // enable javascript
                break;
        }

        return myDriver;
    }
    private static void addAnyValidExtraCapabilityTo(DesiredCapabilities gridCapabilities, Set<String> possibleCapabilityKeys) {

        String extraCapabilityPrefix = "WEBDRIVER_GRID_CAP_X_";

        for(String capabilityName : possibleCapabilityKeys){

            if(capabilityName.startsWith(extraCapabilityPrefix)){

                String capabilityValue = EnvironmentPropertyReader.getPropertyOrEnv(capabilityName, "");

                if(capabilityValue.length()>0){
                    String capability = capabilityName.replaceFirst(extraCapabilityPrefix,"");
                    System.out.println("To Set Capability " + capability + " with value " + capabilityValue);
                    gridCapabilities.setCapability(capability, capabilityValue);
                }
            }
        }
    }
}