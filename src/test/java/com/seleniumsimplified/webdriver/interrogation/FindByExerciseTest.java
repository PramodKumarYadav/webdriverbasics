package com.seleniumsimplified.webdriver.interrogation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FindByExerciseTest {
    private static final String PROTOCOL = "http";
    private static final String DOMAIN = "www.compendiumdev.co.uk";
    private  String hostURL = PROTOCOL + "://" + DOMAIN ;

    private static WebDriver driver;
    private String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
    }
    @BeforeAll
    public static void createDriver() {

        driver = new ChromeDriver();
    }
    @AfterAll
    public static void quitDriver() {

        driver.quit();
    }

    @Test
    public void findElementByIDandAssertGetTextAndGetAttribute() throws MalformedURLException {
        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        WebElement paraID = driver.findElement(By.id("p1"));

        for(int i=0; i<25; i++){
            paraID = driver.findElement(By.id("p"+ (i+1)));

            System.out.println(paraID.getText());
            System.out.println(paraID.getAttribute("id"));
            System.out.println(paraID.findElement(By.id("a" + (i+1))));
            System.out.println(paraID.getAttribute("name"));
            System.out.println(getCharForNumber(i+1));
            System.out.println("----------------");

            assertEquals(paraID.getText(),"This is " + getCharForNumber(i+1).toLowerCase() + " paragraph text","This is a/b/c... paragraph text");
            assertEquals(paraID.getAttribute("name"),"pName"+ (i+1),"name attribute looks like : pName + 'idnumber'");
        }


    }

    @Test
    public void findElementByIDAndAssertName() throws MalformedURLException {
        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        WebElement paraID = driver.findElement(By.id("p1"));

        for(int i=0; i<25; i++){
            paraID = driver.findElement(By.id("p"+ (i+1)));

            assertEquals("pName"+ (i+1),paraID.getAttribute("name"));
        }

    }

    @Test
    public void findElementBylinkTextAndAssertName() throws MalformedURLException {
        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        WebElement paraID = driver.findElement(By.id("p1"));

        for(int i=0; i<25; i++){
            System.out.println(paraID.getTagName());

            paraID = driver.findElement(By.linkText("jump to para " +  i ));

            assertEquals("aName"+ (i+26),paraID.getAttribute("name"));
        }

    }

    @Test
    public void findElementByNameAndAssertId() throws MalformedURLException {
        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        WebElement paraID = driver.findElement(By.id("p1"));

        for(int i=0; i<25; i++){
            paraID = driver.findElement(By.name("pName"+ (i+1)));

            assertEquals("p"+ (i+1),paraID.getAttribute("id"));
        }
    }

    @Test
    public void findElementBypartialLinkTextAndAssertID() throws MalformedURLException {
        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        List<WebElement> elements = driver.findElements(By.partialLinkText("jump to"));

        for(int i=0; i<25; i++){

            assertEquals("a" + (i+26),elements.get(i).getAttribute("id"));
        }

    }

    @Test
    public void findElementBypartialLinkTextMidTextAndAssertID() throws MalformedURLException {
        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        List<WebElement> elements = driver.findElements(By.partialLinkText("to"));
        System.out.println(elements.size());
        assertEquals(25,elements.size());

        for(int i=0; i<elements.size(); i++){
            assertEquals("a" + (i + elements.size() + 1),elements.get(i).getAttribute("id"));
        }

    }

    @Test
    public void findElementBypartialLinkTextMidTextWithTwoMatchesAndAssertID() throws MalformedURLException {
        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        List<WebElement> elements = driver.findElements(By.partialLinkText("7"));

        System.out.println(elements.size());
        assertEquals(2,elements.size());

        assertEquals("jump to para 7",elements.get(0).getText());
//
//        for(int i=0; i<elements.size(); i++){
//
//            assertEquals("jump to para " + i + "7",elements.get(i).getText());
//        }

    }
    @Test
    public void findElementByclassNameAndAssertID() throws MalformedURLException {

        String endPoint = "/selenium/find_by_playground.php";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);

        List<WebElement> elements = driver.findElements(By.className("normal"));
        System.out.println(elements.size());

        for(int i=0; i<25; i++){
            System.out.println(elements.get(i).getText());
//            assertEquals(elements.get(i).getText(),"This is " + getCharForNumber(i+1).toLowerCase() + " paragraph text","This is a/b/c... paragraph text");
        }
    }
}
