package com.seleniumsimplified.webdriver.interrogation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindByCSSTest {

    private static final String PROTOCOL = "http";
    private static final String DOMAIN = "www.compendiumdev.co.uk";
    private  String hostURL = PROTOCOL + "://" + DOMAIN ;

    static WebDriver driver;
    @BeforeAll
    public static void createDriver() {

        driver = new ChromeDriver();
    }

    @AfterAll
    public static void quitDriver() {

        driver.quit();
    }

    @Test
    public void pathNavigationIDinCSSselctor() throws MalformedURLException {

        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        WebElement element = driver.findElement(By.id("p31"));
        System.out.println(element.getText());

        List<WebElement> elements = driver.findElements(By.cssSelector("#p31"));
        System.out.println(elements.size());
        assertEquals("pName31",elements.get(0).getAttribute("name"));

    }
    @Test
    public void pathNavigationFindName() throws MalformedURLException {

        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        List<WebElement> elements = driver.findElements(By.name("ulName1"));
        System.out.println(elements.size());

        elements = driver.findElements(By.cssSelector("[name='ulName1']"));
        System.out.println(elements.size());
        assertEquals("ul1",elements.get(0).getAttribute("id"));
    }

    @Test
    public void pathNavigationClassNameInCSSselector() throws MalformedURLException {

        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        List<WebElement> elements = driver.findElements(By.className("specialDiv"));
        System.out.println(elements.size());

        elements = driver.findElements(By.cssSelector(".specialDiv"));
        System.out.println(elements.size());
        assertEquals("div1",elements.get(0).getAttribute("id"));
    }

    @Test
    public void pathTagName() throws MalformedURLException {

        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        List<WebElement> elements = driver.findElements(By.tagName("li"));
        System.out.println(elements.size());

        elements = driver.findElements(By.cssSelector("li"));
        System.out.println(elements.size());
        assertEquals("liName1",elements.get(0).getAttribute("name"));
    }
}
