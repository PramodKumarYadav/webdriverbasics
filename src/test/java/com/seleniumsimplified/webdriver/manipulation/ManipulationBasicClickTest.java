package com.seleniumsimplified.webdriver.manipulation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManipulationBasicClickTest {

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

    @BeforeEach
    public void setupTestURL() throws MalformedURLException {

        String endPoint = "/selenium/basic_ajax.html";
        URL url = new URL(PROTOCOL,DOMAIN,endPoint);

        driver.navigate().to(url);
    }
    @Test
    public void sanityCSStest() {

        // Find the element 3. <option value="3">Server</option> #combo1 > option:nth-child(3)
        WebElement element = driver.findElement(By.cssSelector("#combo1 > option:nth-child(3)"));
        // WebElement element = driver.findElement(By.cssSelector("#combo1 > option:nth-child(3)"));
        System.out.println(element.getText());
        element.click();

        driver.findElement(By.cssSelector("#combo2 > option:nth-child(4)")).click();
        System.out.println(element.getText());
        element.click();
    }
    @Test
    public void myFirstManipulation() {

        WebElement element = driver.findElement(By.cssSelector("#combo1 > option:nth-child(3)"));
        System.out.println(element.getText());
        element.click();

        new WebDriverWait(driver,10,50).until(ExpectedConditions.titleIs("Basic Ajax"));
        // #combo2 > option:nth-child(4)
        element = driver.findElement(By.cssSelector("#combo2 > option:nth-child(4)"));
        System.out.println(element.getText());
        element.click();

        new WebDriverWait(driver,10,50).until(ExpectedConditions.titleIs("Basic Ajax"));

        element = driver.findElement(By.name("submitbutton"));
        System.out.println(element.getText());
        element.click();

        new WebDriverWait(driver,10,50).until(ExpectedConditions.titleIs("Processed Form Details"));

        element = driver.findElement(By.cssSelector("#_valuelanguage_id"));
        System.out.println(element.getText());
        element.click();

        assertThat(driver.findElement(By.cssSelector("#_valuelanguage_id")).getText(),is("23"));
    }
    @Test
    public void pathNavigationFindName() throws MalformedURLException {

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
