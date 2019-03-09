package com.seleniumsimplified.webdriver.navigation;

import org.eclipse.jetty.io.EndPoint;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavigationExampleTest {

    private static final String PROTOCOL = "http";
    private static final String DOMAIN = "www.compendiumdev.co.uk";
    private  String hostURL = PROTOCOL + "://" + DOMAIN ;

    private static WebDriver driver;

    @BeforeAll
    public static void createDriver() {
        driver = new FirefoxDriver();
    }

    @Test
    public void navigateWithNavigateToSelenium() {
        driver.navigate().to(hostURL + "/selenium/");

        assertTrue(driver.getTitle().startsWith("Selenium Simplified"));
    }
    @Test
    public void navigateWithNavigateToSearchPHP() {
        driver.navigate().to(hostURL + "/selenium/search.php");

        assertTrue(driver.getTitle().startsWith("Selenium Simplified Search Engine"));
    }
    @Test
    public void navigateWithNavigateToBasicHTMLForm() {
        driver.navigate().to(hostURL + "/selenium/basic_html_form.html");
        assertTrue(driver.getTitle().startsWith("HTML Form Elements"));
    }
    @Test
    public void navigateWithNavigateToBasicWebPage() {
        driver.navigate().to(hostURL + "/selenium/basic_web_page.html");

        assertTrue(driver.getTitle().startsWith("Basic Web Page Title"));
    }
    @Test
    public void navigateWithNavigateToRefresh()  {
        driver.navigate().to(hostURL + "/selenium/refresh.php");
        System.out.println(driver.getTitle());

        final String refreshTitleConstant = "Refreshed Page on ";
        assertTrue(driver.getTitle().startsWith(refreshTitleConstant));

        // Get the time stamp in this title and assert that the title you get after refresh is bigger than this
        long startTime = Long.parseLong(driver.getTitle().replaceFirst(refreshTitleConstant, ""));

        try{Thread.sleep(2000);}
        catch (InterruptedException e){}

        driver.navigate().refresh();
        System.out.println(driver.getTitle());
        assertTrue(driver.getTitle().startsWith(refreshTitleConstant));
        long endTime = Long.parseLong(driver.getTitle().replaceFirst(refreshTitleConstant, ""));

        assertTrue(endTime > startTime, "expected " +  endTime + " > " + startTime);
    }

    @Test
    public void navigateWithNavigateToForwardBackAndRefresh() throws MalformedURLException {

        //        driver.navigate().to(hostURL + "selenium/basic_html_form.html");
        String endPoint = "/selenium/basic_html_form.html";
        URL searchPage = new URL(PROTOCOL, DOMAIN, endPoint);
        driver.navigate().to(searchPage);
        assertTrue(driver.getTitle().startsWith("HTML Form Elements"));

        endPoint = "/selenium/refresh.php";
        searchPage = new URL(PROTOCOL, DOMAIN, endPoint);
        driver.navigate().to(searchPage);
        assertTrue(driver.getTitle().startsWith("Refreshed Page "));

        driver.navigate().back();
        System.out.println(driver.getTitle());
        assertTrue(driver.getTitle().startsWith("HTML Form Elements"));

        driver.navigate().forward();
        System.out.println(driver.getTitle());
        assertTrue(driver.getTitle().startsWith("Refreshed Page "));

        driver.navigate().refresh();
        System.out.println(driver.getTitle());
        assertTrue(driver.getTitle().startsWith("Refreshed Page "));

    }
    @AfterAll
    public static void quitDriver() {
        driver.quit();
    }

}
