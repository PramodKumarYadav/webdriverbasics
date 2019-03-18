package com.seleniumsimplified.webdriver.interrogation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.general.methods.NumberToAlphabet.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindElementsExercise {

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
    public void findAllElementsWithTagDiv() throws MalformedURLException {
        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        List<WebElement> elements = driver.findElements(By.tagName("div"));
        System.out.println(elements.size());
    }

    @Test
    public void findAllElementsWithTagA() throws MalformedURLException {
        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        List<WebElement> elements = driver.findElements(By.tagName("p") );
        System.out.println(elements.size());
    }

    @Test
    public void doesFindElementsThrowAnExceptionIfNoMatch() throws MalformedURLException {
        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        List<WebElement> elements = new ArrayList<>();
        elements = driver.findElements(By.tagName("pramod") );

        System.out.println(elements.size());
    }

    @Test
    public void findAllElementsWithTagAttributeHref() throws MalformedURLException {
        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        List<WebElement> elements = driver.findElements(By.partialLinkText("jump to para") );
        System.out.println(elements.size());
        assertEquals(25,elements.size());

        //By using xpath
        elements = driver.findElements(By.xpath("//a[@href]") );
        System.out.println(elements.size());
        assertEquals(25,elements.size());
    }

    @Test
    public void findAllElementsWithNestedTags() throws MalformedURLException {
        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        List<WebElement> elements = driver.findElements(By.tagName("p") );
        System.out.println(elements.size());
        assertEquals(41,elements.size());

        //By using xpath
        int countOfNestedElements = 0;

        for (WebElement e : elements){
            if (e.getText().contains("nested para")) {
                countOfNestedElements++;
            }
        }

        System.out.println(countOfNestedElements);
        assertEquals(16,countOfNestedElements);

    }

    @Test
    public void aByChainedExample() throws MalformedURLException {

        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        WebElement element;
        element = driver.findElement(
                new ByChained(
                        By.id("div1"),
                        By.name("pName9"),
                        By.tagName("a")));

        System.out.println(element.getAttribute("id"));
    }

    @Test
    public void aByChainedExampleUsingFindElement() throws MalformedURLException {

        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        WebElement element;
        element = driver.findElement( By.id("div1"))
                        .findElement(By.name("pName9"))
                        .findElement( By.tagName("a"));

        System.out.println(element.getAttribute("id"));
    }
}
