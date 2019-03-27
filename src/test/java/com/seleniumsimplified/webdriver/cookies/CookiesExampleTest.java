package com.seleniumsimplified.webdriver.cookies;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CookiesExampleTest {

    static WebDriver driver;
    public WebElement searchBox;
    public WebElement submitButton;

    @BeforeAll
    public static void createDriver(){

        driver = new ChromeDriver();
    }

    @AfterAll
    public static void quitDriver() {

        driver.quit();
    }
    @BeforeEach
    public void pageSetUp() {

        driver.navigate().to("http://compendiumdev.co.uk/selenium/search.php");
        driver.manage().deleteAllCookies();

        getPageObjects();
    }

    public void getPageObjects() {

        System.out.println("Set page objects for this test");
        searchBox = driver.findElement(By.name("q"));
        submitButton = driver.findElement(By.name("btnG"));

    }
    @Test
    public void visitSearchPageAndCheckNoLastSearchCookie() {

        driver.navigate().refresh();

        Cookie cookie = driver.manage().getCookieNamed("seleniumSimplifiedLastSearch");

        assertEquals(null, cookie,"Should return no cookies");
    }

    @Test
    public void visitSearchPageAndCheckNumberOfVisitsIsOne() {

        driver.navigate().refresh();

        Cookie cookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");

        assertEquals("1", cookie.getValue(),"Should return 1");
    }

    @Test
    public void visitSearchPageAndSetVisitValue() {

        driver.navigate().refresh();

        Cookie cookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
        assertEquals("1", cookie.getValue(),"Should return 1");

        Cookie newCookie = new Cookie(
                cookie.getName(),
                "42",
                cookie.getDomain(),
                cookie.getPath(),
                cookie.getExpiry(),
                cookie.isSecure(),
                cookie.isHttpOnly()
        );

        driver.manage().deleteCookie(cookie);
        driver.manage().addCookie(newCookie);

        getPageObjects();

        searchBox.clear();
        searchBox.sendKeys("Cookie test");
        submitButton.click();

        cookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");

        assertEquals("43", cookie.getValue(),"Should return 43");

    }
    @Test
    public void createNewCookie(){

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        Cookie cookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
        System.out.println(cookie.getName());
        System.out.println(cookie.getValue());
        System.out.println(cookie.getDomain());
        System.out.println(cookie.getPath());
        System.out.println(cookie.getExpiry());

        System.out.println(cookie.isHttpOnly());
        System.out.println(cookie.isSecure());
        System.out.println(cookie.hashCode());
        System.out.println(cookie.toJson());

        Cookie.Builder anotherCookie = new Cookie.Builder("myCookie","myValue");

    }

    @Test
    public void getAllCookies() {

        driver.navigate().refresh();

        getPageObjects();

        searchBox.clear();
        searchBox.sendKeys("Cookie test");
        submitButton.click();

        Set<Cookie> cookies = driver.manage().getCookies();

        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + " : " + cookie.getValue());
        }



    }
}
