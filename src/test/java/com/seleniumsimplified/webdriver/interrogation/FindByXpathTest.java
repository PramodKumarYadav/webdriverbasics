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

public class FindByXpathTest {

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
    public void pathNavigation() throws MalformedURLException {
        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='specialDiv']//p[@id='p1' or @id='p2' or @id='p3' or @name='pName4']"));
        System.out.println(elements.size());
    }

    @Test
    public void pathNavigationFindAParagraph() throws MalformedURLException {
        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        List<WebElement> elements = driver.findElements(By.id("p31"));
        System.out.println(elements.size());

        elements = driver.findElements(By.xpath("//p[@id='p31']"));
        System.out.println(elements.size());
        assertEquals("pName31",elements.get(0).getAttribute("name"));

        elements = driver.findElements(By.xpath("//*[@id='p31']"));
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

        elements = driver.findElements(By.xpath("//ul[@name=\"ulName1\"]"));
        System.out.println(elements.size());
        assertEquals("ul1",elements.get(0).getAttribute("id"));
    }

    @Test
    public void pathNavigationClassName() throws MalformedURLException {

        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        List<WebElement> elements = driver.findElements(By.className("specialDiv"));
        System.out.println(elements.size());

        elements = driver.findElements(By.xpath("//div[@class='specialDiv']"));
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

        elements = driver.findElements(By.xpath("//li[@id='li1']"));
        System.out.println(elements.size());
        assertEquals("liName1",elements.get(0).getAttribute("name"));
    }
}
